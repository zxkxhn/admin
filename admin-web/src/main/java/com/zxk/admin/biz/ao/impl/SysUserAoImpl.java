package com.zxk.admin.biz.ao.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.admin.biz.enums.SysUserStatusEnum;
import com.zxk.admin.biz.exception.SysException;
import com.zxk.admin.biz.form.SysUserAddForm;
import com.zxk.admin.biz.query.SysUserQuery;
import com.zxk.admin.biz.vo.SysUserVO;
import com.zxk.core.common.PageVO;
import com.zxk.core.common.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

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
    public Result<PageVO<SysUserVO>> selectList(SysUserQuery sysUserQuery) {
        IPage<SysUser> page = sysUserDao.selectPage(sysUserQuery.page(), new QueryWrapper<>());
        List<SysUserVO> list = new ArrayList<>();
        page.getRecords().forEach(sysUser -> {
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtil.copyProperties(sysUserVO, sysUser);
            list.add(sysUserVO);
        });
        return Result.page(list,page);
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
