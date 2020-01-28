package com.zxk.admin.biz.ao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.admin.biz.form.SysUserAddForm;
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
     * 创建用户
     * @param sysUserAddForm 添加用户表单
     */
    Result<Void> addUser(SysUserAddForm sysUserAddForm);

    /**
     * 列表
     */
    Result<Page<SysUser>> selectList();

}
