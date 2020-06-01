package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ss.core.common.BaseDO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@TableName("sys_dict_detail")
public class SysDictDetail extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典值
     */
    private String value;

    /**
     * 排序
     */
    private Integer dictSort = 999;
}