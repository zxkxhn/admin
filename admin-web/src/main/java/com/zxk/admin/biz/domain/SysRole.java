package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zxk.core.common.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年11月30日 21:16
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
public class SysRole extends BaseDO {

    private static final long serialVersionUID = 5029580273526475724L;


    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

}
