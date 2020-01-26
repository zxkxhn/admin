package com.zxk.admin.biz.form.login;

import com.zxk.core.util.validation.IsMobile;
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
@ApiModel(description = "用户手机登录表单")
public class SysUserMobileLoginForm extends SysUserLoginForm {

    @ApiModelProperty(value = "手机号")
    @IsMobile()
    @NotBlank(message = "手机号不能为空")
    private String mobile;
}
