package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ss.core.common.BaseDO;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单
 */
@Getter
@Setter
@TableName("sys_menu")
public class SysMenu extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 上级菜单
     */
    private long pid;

    /**
     * 菜单类型，目录、菜单、按钮
     */
    private int type;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 组件名称
     */
    private String componentName;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 排序
     */
    private int menuSort = 999;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 缓存
     */
    private boolean cache;

    /**
     * 是否隐藏
     */
    private boolean hidden;

    /**
     * 外链菜单
     */
    private boolean iFrame;
}
