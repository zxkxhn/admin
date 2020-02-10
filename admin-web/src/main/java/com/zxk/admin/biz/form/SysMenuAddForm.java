package com.zxk.admin.biz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "添加菜单" , discriminator = "SysMenu")
public class SysMenuAddForm implements Serializable {

    private static final long serialVersionUID = 3237974578451583202L;

    @ApiModelProperty(value = "父id")
    private long parentId = -1;

    @ApiModelProperty(value = "添加类型")
    private int type;

    @NotBlank(message = "菜单名为空")
    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "URL")
    private String url;

    @NotBlank(message = "权限名不为空")
    @ApiModelProperty(value = "权限名")
    private String perms;

    @NotBlank(message = "菜单图标")
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private int sort;
}