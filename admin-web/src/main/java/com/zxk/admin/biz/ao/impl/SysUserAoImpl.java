package com.zxk.admin.biz.ao.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
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
import com.zxk.core.config.security.constant.SecurityConstant;
import com.zxk.core.util.RedisUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * @Date 2019年12月01日 11:40
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

//        // 随机key
//        SecretKey key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue());
//        //构建
//        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getEncoded());
//
//        //加密为16进制表示
//        String encryptHex = aes.encryptHex(sysUser.getPassword());
//        String base64Key = Base64.encode(key.getEncoded());

//        sysUser.setPassword(encryptHex);
//        sysUser.setSalt(base64Key);
        String encryptPass = new BCryptPasswordEncoder().encode(sysUser.getPassword());
        sysUser.setPassword(encryptPass);
        sysUser.setStatus(SysUserStatusEnum.normal.getValue());

        int i = sysUserDao.insert(sysUser);
        if (i > 0) {
            return Result.success();
        }
        return Result.fail();
    }

    @Override
    @Transactional(rollbackFor = SysException.class)
    public Result<String> resetPass(long id) {
        SysUser sysUser = sysUserDao.selectById(id);
        if (sysUser == null) {
            throw new SysException("未找到对应ID的用户,请重试!");
        }
        //  创建6-12位随机密码
        String password = RandomUtil.randomString(RandomUtil.randomInt(6, 12));
        String encryptPass = new BCryptPasswordEncoder().encode(password);
        SysUser editSysUser = new SysUser();
        BeanUtil.copyProperties(sysUser, editSysUser);
        editSysUser.setGmtModified(DateUtil.date());
        editSysUser.setPassword(encryptPass);

        int i = sysUserDao.updateById(editSysUser);
        if (i == 0) {
            throw new SysException("重置密码失败,请重试!");
        }

        String username = editSysUser.getUsername();

        if(!RedisUtils.getSingleton().hasKey(SecurityConstant.USER_TOKEN + username)){
            return Result.success(password);
        }
        String token = (String) RedisUtils.getSingleton().get(SecurityConstant.USER_TOKEN + username);
        if (!RedisUtils.getSingleton().hasKey(SecurityConstant.TOKEN_PRE + token)) {
            return Result.success(password);
        }
        RedisUtils.getSingleton().delete(SecurityConstant.USER_TOKEN + username);
        RedisUtils.getSingleton().delete(SecurityConstant.TOKEN_PRE + token);
        return Result.success(password);
    }

    @Override
    public Result<PageVO<SysUserVO>> selectList(SysUserQuery sysUserQuery) {
        IPage<SysUser> page = sysUserDao.selectPage(sysUserQuery.page(), new QueryWrapper<>());
        List<SysUserVO> list = new ArrayList<>();
        page.getRecords().forEach(sysUser -> {
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtil.copyProperties(sysUser, sysUserVO);
            list.add(sysUserVO);
        });
        return Result.page(list, page);
    }


    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
