package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ss.core.common.BaseDO;

/**
 * 用户角色关联表
 */
@TableName("sys_user_role")
public class SysUserRole extends BaseDO {

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 角色ID
     */
    private long roleId;

}
