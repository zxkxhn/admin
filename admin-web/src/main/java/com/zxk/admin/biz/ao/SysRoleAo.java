package com.zxk.admin.biz.ao;

import com.ss.core.common.Result;
import com.zxk.admin.biz.form.SysRoleAddForm;
import com.zxk.admin.biz.form.SysRoleEditForm;
import com.zxk.admin.biz.vo.SysRoleVo;

import java.util.List;

public interface SysRoleAo {


    /**
     * 新增角色
     *
     * @param sysRoleAddForm 添加角色表单
     */
    Result<Void> add(SysRoleAddForm sysRoleAddForm);


    /**
     * 删除角色
     *
     * @param id 角色id
     */
    Result<Void> del(long id);

    /**
     * 编辑角色
     *
     * @param sysRoleEditForm 编辑角色表单
     */
    Result<Void> edit(SysRoleEditForm sysRoleEditForm);

    /**
     * 查询角色详情
     *
     * @param id 角色ID
     */
    Result<SysRoleVo> queryById(long id);

    /**
     * 搜索出所有的角色
     */
    Result<List<SysRoleVo>> queryAll();


}
