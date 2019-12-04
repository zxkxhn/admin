package com.zxk.admin.biz.ao;

import com.zxk.admin.biz.form.UserAddForm;
import com.zxk.admin.biz.form.UserLoginForm;
import com.zxk.admin.biz.form.UserRegisterForm;
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
    Result login(UserLoginForm userLoginForm);

    /**
     * 注册
     */
    Result register(UserRegisterForm userRegisterForm);

    /**
     * 创建用户
     * @param  userAddForm
     */
    Result addUser(UserAddForm userAddForm);

    /**
     * 列表
     */
    Result selectList();

}
