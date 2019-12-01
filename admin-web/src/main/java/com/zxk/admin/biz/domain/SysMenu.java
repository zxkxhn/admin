package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zxk.core.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年11月30日 21:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_menu")
public class SysMenu extends BaseDO {
    private static final long serialVersionUID = 3210231759707597912L;

    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 父类ID
     */
    private long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private int type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private int sort;
}
