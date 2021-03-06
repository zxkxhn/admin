package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ss.core.common.BaseDO;

/**
 * 角色部门关联表
 */
@TableName("sys_role_dept")
public class SysRoleDept extends BaseDO {

    /**
     * 角色ID
     */
    private long roleId;

    /**
     *  部门ID
     */
    private long deptId;
}
