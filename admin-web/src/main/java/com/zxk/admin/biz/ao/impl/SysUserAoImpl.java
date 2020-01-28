package com.zxk.admin.biz.ao.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.admin.biz.enums.SysUserStatusEnum;
import com.zxk.admin.biz.exception.SysException;
import com.zxk.admin.biz.form.SysUserAddForm;
import com.zxk.admin.biz.form.login.SysUserLoginForm;
import com.zxk.core.common.Result;
import com.zxk.core.util.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.crypto.SecretKey;

import static com.zxk.core.config.security.constant.SecurityConstant.LOGIN_CAPTCHA_ID;

/**
 * 用户管理
 *
 * @author zhangxk
 * @Email  980137428@qq.com
 * @Date   2019年12月01日 11:40
 */
@Service
public class SysUserAoImpl implements SysUserAo {

    @Resource
    private SysUserDao sysUserDao;

    /**
     * 校验验证码
     * @param sysUserLoginForm 通用表单
     */
    private boolean checkCaptchaCode(SysUserLoginForm sysUserLoginForm) {
        if (!RedisUtils.getSingleton().hasKey(LOGIN_CAPTCHA_ID + sysUserLoginForm.getCaptchaImageId())) {
            return false;
        }
        return !RedisUtils.getSingleton().get(LOGIN_CAPTCHA_ID + sysUserLoginForm.getCaptchaImageId()).equals(sysUserLoginForm.getCaptchaCode());
    }

    /**
     * 校验密码
     * @param salt 盐
     * @param password 密码
     * @param passwordInput 输入的密码
     */
    private boolean checkPassword(String salt, String password, String passwordInput) {
        byte[] key = Base64.decode(salt);
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //解密字符串并校验
        return password.equals(aes.encryptHex(passwordInput, CharsetUtil.CHARSET_UTF_8));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> addUser(SysUserAddForm sysUserAddForm) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserAddForm, sysUser);

        int count = sysUserDao.selectCount(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, sysUser.getUsername()));
        if (count > 0) {
            throw new SysException("用户名重复!!");
        }
        count = sysUserDao.selectCount(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getEmail, sysUser.getEmail()));

        if (count > 0) {
            throw new SysException("用户邮箱重复!!");
        }
        count = sysUserDao.selectCount(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getMobile, sysUser.getMobile()));

        if (count > 0) {
            throw new SysException("用户手机号重复!!");
        }

        // 随机key
        SecretKey key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue());
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getEncoded());

        //加密为16进制表示
        String encryptHex = aes.encryptHex(sysUser.getPassword());
        String base64Key = Base64.encode(key.getEncoded());

        sysUser.setPassword(encryptHex);
        sysUser.setSalt(base64Key);
        sysUser.setStatus(SysUserStatusEnum.normal.getValue());

        int i = sysUserDao.insert(sysUser);
        if (i > 0) {
            return Result.success();
        }
        return Result.fail();
    }

    @Override
    public Result<Page<SysUser>> selectList() {
        return Result.success();
    }


    public static void main(String[] args) {
        String passwd = "zxkxhn";

        //随机生成密钥
        SecretKey key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue());

        String base = Base64.encode(key.getEncoded());
        System.out.println(base);

        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getEncoded());

        //加密
        byte[] encrypt = aes.encrypt(passwd);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);

        //加密为16进制表示
        String encryptHex = aes.encryptHex(passwd);
        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(encryptHex);


    }
}
