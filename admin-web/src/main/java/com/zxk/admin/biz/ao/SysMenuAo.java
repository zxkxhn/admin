package com.zxk.admin.biz.ao;

import com.zxk.admin.biz.form.SysMenuAddForm;
import com.zxk.admin.biz.form.SysUserEditForm;
import com.zxk.admin.biz.query.SysUserQuery;
import com.zxk.admin.biz.vo.SysUserVo;
import com.ss.core.common.PageVO;
import com.ss.core.common.Result;

/**
 * 用户管理AO
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年12月01日 11:39
 */
public interface SysMenuAo {

    /**
     * 创建菜单
     * @param sysMenuAddForm 添加用户表单
     */
    Result<Void> add(SysMenuAddForm sysMenuAddForm);

    /**
     * 删除菜单
     * @param id 用户ID
     */
    Result<Void> del(long id);

    /**
     * 修改菜单
     *
     * @param sysUserEditForm 用户ID
     */
    Result<Void> update(SysUserEditForm sysUserEditForm);

    /**
     * 列表
     */
    Result<PageVO<SysUserVo>> queryPage(SysUserQuery sysUserQuery);


    /**
     * 通过ID查询用户
     * @param id 用户ID
     * @return
     */
    Result<SysUserVo> queryById(long id);


}
