package com.zxk.admin.biz.vo;

import com.zxk.core.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel(value = "用户列表返回", description = "系统用户")
public class SysUserVo extends BaseVO {

    private static final long serialVersionUID = 5846712330152995264L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "ID", example = "1")
    private long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 状态  1：禁用   0：正常
     */
    @ApiModelProperty(value = "用户状态 0,正常;1,冻结", example = "1")
    private int status;
}
