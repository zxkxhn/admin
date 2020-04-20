package com.zxk.admin.biz.vo;

import com.ss.core.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "菜单", description = "系统菜单")
public class SysMenuVo extends BaseVO {
    private static final long serialVersionUID = 7186714520119522401L;

    @ApiModelProperty(value = "菜单ID")
    private long id;

    @ApiModelProperty(value = "父类ID")
    private long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单URL")
    private String url;

    @ApiModelProperty(value = "授权")
    private String perms;

    @ApiModelProperty(value = "菜单类型")
    private int type;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "菜单排序")
    private int sort;

    @ApiModelProperty(value = "子类菜单")
    private List<SysMenuVo> childSysMenu;
}
