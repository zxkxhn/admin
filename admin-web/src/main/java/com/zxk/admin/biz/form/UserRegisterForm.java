package com.zxk.admin.biz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用户注册表单
 *
 * @author xiaokun.zhang
 * Date:   2019年12月04日 15:06
 * @version 1.0
 */
@Data
@ApiModel(description = "注册 form")
public class UserRegisterForm {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String password;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email
    private String email;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;


}
