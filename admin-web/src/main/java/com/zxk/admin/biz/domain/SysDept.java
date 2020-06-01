package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ss.core.common.BaseDO;
import lombok.Getter;
import lombok.Setter;


/**
 * 部门表
 */
@Getter
@Setter
@TableName("sys_dept")
public class SysDept extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 上级部门
     **/
    private long pid;

    /**
     * 子节点数目
     **/
    private long subCount;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private int deptSort;

    /**
     * 是否启用
     **/
    private boolean enabled;
}