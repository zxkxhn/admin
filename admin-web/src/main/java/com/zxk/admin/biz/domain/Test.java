package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zxk.core.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户表
 *
 * @author xiaokun.zhang
 * Date:   2019年11月19日 16:19
 * @version 1.0
 */

@Data
@TableName("test")
public class Test implements Serializable {

    private static final long serialVersionUID = 5755804560720746566L;
    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private long id;

}


