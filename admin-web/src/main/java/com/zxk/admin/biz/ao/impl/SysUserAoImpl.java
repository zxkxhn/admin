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
import com.zxk.admin.biz.form.SysUserEditForm;
import com.zxk.admin.biz.query.SysUserQuery;
import com.zxk.admin.biz.vo.SysUserVo;
import com.ss.core.common.PageVO;
import com.ss.core.common.Result;
import com.ss.core.config.security.constant.SecurityConstant;
import com.ss.core.config.security.service.SecurityService;
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

    @Resource
    private SecurityService securityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> add(SysUserAddForm sysUserAddForm) {
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
    public Result<Void> del(long id) {
        SysUser sysUser = sysUserDao.selectById(id);
        if(sysUser == null || sysUser.getUsername().equals(SecurityConstant.LOGIN_ADMIN)){
            throw new SysException("管理员账户禁止操作");
        }

        if (sysUserDao.deleteById(id) == 0) {
            return Result.fail("删除失败");
        }
        return Result.success();
    }

    @Override
    public Result<Void> update(SysUserEditForm sysUserEditForm) {
        long id = sysUserEditForm.getId();
        SysUser sysUser = sysUserDao.selectById(id);
        if (sysUser == null) {
            throw new SysException("未找到对应ID的用户,请重试!");
        }
        if(sysUser.getUsername().equals(SecurityConstant.LOGIN_ADMIN)){
            throw new SysException("管理员账户禁止操作");
        }
        BeanUtil.copyProperties(sysUserEditForm, sysUser,"id");
        sysUser.setGmtModified(DateUtil.date());
        if (sysUserDao.updateById(sysUser) == 0) {
            return Result.fail("修改失败!");
        }
        return Result.success();
    }

    @Override
    public Result<Void> updatePassword(long id, String password, String newPassword) {
        SysUser sysUser = sysUserDao.selectById(id);
        if (sysUser == null) {
            throw new SysException("未找到对应ID的用户,请重试!");
        }
        if(sysUser.getUsername().equals(SecurityConstant.LOGIN_ADMIN)){
            throw new SysException("管理员账户禁止操作");
        }
        if (!new BCryptPasswordEncoder().matches(password, sysUser.getPassword())) {
            return Result.fail("旧密码不正确");
        }

        String newEncryptPass = new BCryptPasswordEncoder().encode(newPassword);
        sysUser.setPassword(newEncryptPass);
        if(sysUserDao.updateById(sysUser) == 0){
            return Result.fail("修改密码失败!");
        }

        securityService.clearUserToken(sysUser.getUsername());
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = SysException.class)
    public Result<String> resetPass(long id) {
        SysUser sysUser = sysUserDao.selectById(id);
        if (sysUser == null) {
            throw new SysException("未找到对应ID的用户,请重试!");
        }

        if(sysUser.getUsername().equals(SecurityConstant.LOGIN_ADMIN)){
            throw new SysException("管理员账户禁止操作");
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

        securityService.clearUserToken(sysUser.getUsername());
        return Result.success(password);
    }

    @Override
    public Result<PageVO<SysUserVo>> queryPage(SysUserQuery sysUserQuery) {
        IPage<SysUser> page = sysUserDao.selectPage(sysUserQuery.page(), new QueryWrapper<>());
        List<SysUserVo> list = new ArrayList<>();
        page.getRecords().forEach(sysUser -> {
            SysUserVo sysUserVO = new SysUserVo();
            BeanUtil.copyProperties(sysUser, sysUserVO);
            list.add(sysUserVO);
        });
        return Result.page(list, page);
    }

    @Override
    public Result<SysUserVo> queryById(long id) {
        SysUser sysUser = sysUserDao.selectById(id);
        if(sysUser == null){
            return Result.fail();
        }
        SysUserVo sysUserVO = new SysUserVo();
        BeanUtil.copyProperties(sysUser, sysUserVO);
        return Result.success(sysUserVO);
    }

}
