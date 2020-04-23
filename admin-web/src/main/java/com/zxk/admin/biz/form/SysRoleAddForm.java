package com.zxk.admin.biz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "添加用户角色", discriminator = "SysRole")
public class SysRoleAddForm implements Serializable {

    private static final long serialVersionUID = 6472685504431670364L;


    @ApiModelProperty(value = "角色名", example = "角色名以 ROLE_* 格式为例")
    @NotBlank(message = "角色名不能为空")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;
}
