package com.zxk.admin.biz.ao.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.admin.biz.form.UserAddForm;
import com.zxk.admin.biz.form.UserLoginForm;
import com.zxk.admin.biz.form.UserRegisterForm;
import com.zxk.core.common.Result;
import com.zxk.core.config.shiro.jwt.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户管理
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年12月01日 11:40
 */
@Service
public class SysUserAoImpl implements SysUserAo {

    @Resource
    private SysUserDao sysUserDao;


    @Override
    public Result login(UserLoginForm userLoginForm) {
//        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda()
//                .eq(SysUser::getPassword, userLoginForm.getPassword())
//                .eq(SysUser::getUsername, userLoginForm.getUsername())
//        ;
//        SysUser sysUser = sysUserDao.selectOne(queryWrapper);
//        if(sysUser == null){
//            return Result.fail("登录失败，密码错误");
//        }
        SysUser sysUser = new SysUser();
        sysUser.setUsername("root");
        sysUser.setPassword("root");
        sysUser.setSalt("root");
        BeanUtil.copyProperties(sysUser, sysUser,"gmtModified");
        return Result.success(JwtUtil.sign(sysUser.getUsername(), sysUser.getSalt()));
    }

    @Override
    public Result register(UserRegisterForm userRegisterForm) {
        Result result = Result.fail();
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(userRegisterForm, sysUser);
        int i = sysUserDao.insert(sysUser);
        if (i > 0) {
            return Result.success("注册成功");
        }
        return result;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addUser(UserAddForm userAddForm) {
        Result result = Result.fail();
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(userAddForm, sysUser);
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


}
