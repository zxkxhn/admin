package com.zxk.admin.biz.ao.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.admin.biz.enums.SysUserStatusEnum;
import com.zxk.admin.biz.exception.SysException;
import com.zxk.admin.biz.form.SysUserAddForm;
import com.zxk.admin.biz.form.SysUserLoginForm;
import com.zxk.core.common.Result;
import com.zxk.core.config.shiro.jwt.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.crypto.SecretKey;

/**
 * 用户管理
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * @Date: 2019年12月01日 11:40
 */
@Service
public class SysUserAoImpl implements SysUserAo {

    @Resource
    private SysUserDao sysUserDao;


    @Override
    public Result login(SysUserLoginForm sysUserLoginForm) {
        SysUser sysUser = sysUserDao.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, sysUserLoginForm.getUsername())
        );
        if (sysUser == null) {
            return Result.fail("登录失败,账号不存在");
        }

        byte[] key = Base64.decode(sysUser.getSalt());
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        //解密为字符串
        String passwordForm = aes.encryptHex(sysUserLoginForm.getPassword(), CharsetUtil.CHARSET_UTF_8);
        if (!sysUser.getPassword().equals(passwordForm)) {
            return Result.fail("登录失败,账号密码错误");
        }
        BeanUtil.copyProperties(sysUser, sysUser, "gmtModified");
        return Result.success(JwtUtil.sign(sysUser.getUsername(), sysUser.getSalt()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addUser(SysUserAddForm sysUserAddForm) {
        Result result = Result.fail();
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserAddForm, sysUser);

        int count = sysUserDao.selectCount(new LambdaQueryWrapper<SysUser>()
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
            return Result.success("添加成功");
        }
        return result;
    }

    @Override
    public Result selectList() {
        return Result.success(sysUserDao.selectList(new QueryWrapper<>()));
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
