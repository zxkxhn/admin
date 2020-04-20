package com.zxk.admin.biz.ao;

import com.zxk.admin.biz.form.SysUserAddForm;
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
public interface SysUserAo {

    /**
     * 创建用户
     * @param sysUserAddForm 添加用户表单
     */
    Result<Void> add(SysUserAddForm sysUserAddForm);

    /**
     * 删除用户
     * @param id 用户ID
     */
    Result<Void> del(long id);

    /**
     * 修改用户
     *
     * @param sysUserEditForm 用户ID
     */
    Result<Void> update(SysUserEditForm sysUserEditForm);

    /**
     * 修改密码
     *
     * @param id 用户ID
     */
    Result<Void> updatePassword(long id, String password, String newPassword);


    /**
     * 重置密码
     * @param id 用户ID
     * @return 密码
     */
    Result<String> resetPass(long id);

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
