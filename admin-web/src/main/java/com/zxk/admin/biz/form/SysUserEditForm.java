package com.zxk.admin.biz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 用户表单
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年11月30日 21:51
 */
@Data
@ApiModel(value = "编辑用户表单" , discriminator = "SysUser")
public class SysUserEditForm implements Serializable {

    private static final long serialVersionUID = -9066665058485853342L;

    @ApiModelProperty(value = "用户ID")
    private long id;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email
    private String email;

}
