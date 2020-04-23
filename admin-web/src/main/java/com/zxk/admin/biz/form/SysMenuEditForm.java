package com.zxk.admin.biz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "编辑菜单", discriminator = "SysMenu")
public class SysMenuEditForm implements Serializable {

    private static final long serialVersionUID = 3237974578451583202L;


    @NotBlank(message = "菜单名为空")
    @ApiModelProperty(value = "菜单名")
    private String name;


    @ApiModelProperty(value = "Url")
    private String url;

    @NotBlank(message = "权限名不为空")
    @ApiModelProperty(value = "权限名")
    private String perms;

    @NotBlank(message = "菜单图标")
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序不为空")
    private int sort;
}