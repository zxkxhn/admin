package com.zxk.admin.biz.form;

import com.zxk.core.util.validation.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author xiaokun.zhang
 * Date:   2019年12月04日 10:29
 * @version 1.0
 */
@Data
@ApiModel(description = "用户手机登录表单")
public class SysUserMobileLoginForm {

    @ApiModelProperty(value = "手机号")
    @IsMobile()
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String password;




}
