package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zxk.core.util.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单绑定表
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年11月30日 21:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseDO {
    private static final long serialVersionUID = 4679223551282264228L;

    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 角色ID
     */
    private long roleId;

    /**
     * 菜单ID
     */
    private long menuId;

}
