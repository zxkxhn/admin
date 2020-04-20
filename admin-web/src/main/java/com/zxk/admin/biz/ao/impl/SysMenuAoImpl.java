package com.zxk.admin.biz.ao.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zxk.admin.biz.ao.SysMenuAo;
import com.zxk.admin.biz.dao.SysMenuDao;
import com.zxk.admin.biz.dao.SysRoleDao;
import com.zxk.admin.biz.dao.SysRoleMenuDao;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.domain.SysMenu;
import com.zxk.admin.biz.domain.SysRoleMenu;
import com.zxk.admin.biz.exception.SysException;
import com.zxk.admin.biz.form.SysMenuAddForm;
import com.zxk.admin.biz.form.SysUserEditForm;
import com.zxk.admin.biz.query.SysUserQuery;
import com.zxk.admin.biz.vo.SysUserVo;
import com.ss.core.common.PageVO;
import com.ss.core.common.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysMenuAoImpl implements SysMenuAo {

    @Resource
    private SysMenuDao sysMenuDao;

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysRoleDao sysRoleDao;



    @Override
    public Result<Void> add(SysMenuAddForm sysMenuAddForm) {
        SysMenu sysMenu = new SysMenu();
        BeanUtil.copyProperties(sysMenuAddForm, sysMenu);
        int i = sysMenuDao.insert(sysMenu);
        if(i <= 0){
            return Result.fail();
        }
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<Void> del(long id) {
        SysMenu sysMenu = sysMenuDao.selectById(id);
        if(sysMenu == null){
            Result.fail("该菜单ID不存在");
        }
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuDao.selectList(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getMenuId, id));
        if (CollectionUtil.isEmpty(sysRoleMenuList)) {
            Result.fail("关联不存在!");
        }
        int i = sysRoleMenuDao.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getMenuId, id));
        if (i <= 0) {
            throw new SysException("菜单关联角色删除失败");
        }
        return Result.success();
    }

    @Override
    public Result<Void> update(SysUserEditForm sysUserEditForm) {
        return null;
    }

    @Override
    public Result<PageVO<SysUserVo>> queryPage(SysUserQuery sysUserQuery) {
        return null;
    }

    @Override
    public Result<SysUserVo> queryById(long id) {
        return null;
    }



}
