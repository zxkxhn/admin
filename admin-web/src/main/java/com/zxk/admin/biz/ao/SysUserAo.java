package com.zxk.admin.biz.ao;

import com.zxk.admin.biz.domain.SysUser;

/**
 * 用户管理AO
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年12月01日 11:39
 */
public interface SysUserAo {

    /**
     * 创建用户
     * @param  sysUser
     */
    void addUser(SysUser sysUser);

}
