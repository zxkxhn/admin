package com.zxk.admin.biz.form.login;

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
public class SysUserLoginForm{

    @ApiModelProperty(value = "验证码ID")
    @NotBlank(message = "验证码ID不能为空")
    private String captchaImageId;

    @ApiModelProperty(value = "验证码Code")
    @NotBlank(message = "验证码Code不能为空")
    private String captchaCode;

    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String password;
}
