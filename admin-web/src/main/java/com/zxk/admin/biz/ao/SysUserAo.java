package com.zxk.admin.biz.ao;

import com.zxk.admin.biz.form.SysUserAddForm;
import com.zxk.admin.biz.form.SysUserLoginForm;
import com.zxk.admin.biz.form.SysUserMobileLoginForm;
import com.zxk.core.common.Result;

/**
 * 用户管理AO
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年12月01日 11:39
 */
public interface SysUserAo {

    /**
     * 登录
     */
    Result login(SysUserLoginForm sysUserLoginForm);

    /**
     * 登录
     */
    Result login(SysUserMobileLoginForm sysUserMobileLoginForm);

    /**
     * 创建用户
     *
     * @param sysUserAddForm
     */
    Result addUser(SysUserAddForm sysUserAddForm);

    /**
     * 列表
     */
    Result selectList();

}
