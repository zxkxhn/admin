package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ss.core.common.BaseDO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@TableName("sys_dict")
public class SysDict extends BaseDO {

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
     * 描述
     */
    private String description;
}