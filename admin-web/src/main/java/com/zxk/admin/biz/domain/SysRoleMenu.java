package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ss.core.common.BaseDO;

@TableName("sys_role_menu")
public class SysRoleMenu extends BaseDO {

    /**
     * 菜单ID
     */
    private long menuId;

    /**
     * 角色ID
     */
    private long roleId;
}
