package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ss.core.common.BaseDO;
import com.zxk.admin.biz.enums.DataScopeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色关联表
 */
@Getter
@Setter
@TableName("sys_role")
public class SysRole extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 数据权限，全部 、 本级 、 自定义
     */
    private String dataScope = DataScopeEnum.THIS_LEVEL.getValue();

    /**
     * 级别，数值越小，级别越大
     */
    private int level = 3;

    /**
     * 描述
     */
    private String description;
}
