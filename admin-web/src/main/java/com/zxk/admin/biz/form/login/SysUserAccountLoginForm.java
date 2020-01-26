package com.zxk.admin.biz.form.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author xiaokun.zhang
 * Date:   2019年12月04日 10:29
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "用户账号登录表单")
public class SysUserAccountLoginForm extends SysUserLoginForm{

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;



}
