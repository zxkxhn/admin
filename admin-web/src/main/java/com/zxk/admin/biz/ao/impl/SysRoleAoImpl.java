package com.zxk.admin.biz.ao.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ss.core.common.Result;
import com.zxk.admin.biz.ao.SysRoleAo;
import com.zxk.admin.biz.dao.SysRoleDao;
import com.zxk.admin.biz.domain.SysRole;
import com.zxk.admin.biz.form.SysRoleAddForm;
import com.zxk.admin.biz.form.SysRoleEditForm;
import com.zxk.admin.biz.vo.SysRoleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleAoImpl implements SysRoleAo {

    @Resource
    private SysRoleDao sysRoleDao;

    @Override
    public Result<Void> add(SysRoleAddForm sysRoleAddForm) {
        SysRole sysRole = new SysRole();
        BeanUtil.copyProperties(sysRoleAddForm, sysRole);
        sysRole.setGmtCreate(DateUtil.date());
        int i = sysRoleDao.insert(sysRole);
        if (i <= 0) {
            Result.fail();
        }
        return Result.success();
    }

    @Override
    public Result<Void> del(long id) {
        if (sysRoleDao.deleteById(id) <= 0) {
            return Result.fail();
        }
        ;
        return Result.success();
    }

    @Override
    public Result<Void> edit(SysRoleEditForm sysRoleEditForm) {
        SysRole sysRole = new SysRole();
        BeanUtil.copyProperties(sysRoleEditForm, sysRole);
        int i = sysRoleDao.updateById(sysRole);
        if (i <= 0) {
            Result.fail();
        }
        return Result.success();
    }

    @Override
    public Result<SysRoleVo> queryById(long id) {
        SysRole sysRole = sysRoleDao.selectById(id);
        if (sysRole == null) {
            Result.fail("未找到对应ID");
        }
        SysRoleVo sysRoleVo = new SysRoleVo();
        BeanUtil.copyProperties(sysRole, sysRoleVo);
        return Result.success(sysRoleVo);
    }

    @Override
    public Result<List<SysRoleVo>> queryAll() {
        List<SysRoleVo> sysRoleVoList = new ArrayList<>();
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        List<SysRole> sysRoleList = sysRoleDao.selectList(queryWrapper);
        sysRoleList.forEach(sysRole -> {
            SysRoleVo sysRoleVo = new SysRoleVo();
            BeanUtil.copyProperties(sysRole, sysRoleVo);
            sysRoleVoList.add(sysRoleVo);
        });
        return Result.success(sysRoleVoList);
    }
}
