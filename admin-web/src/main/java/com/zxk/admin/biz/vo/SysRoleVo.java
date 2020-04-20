package com.zxk.admin.biz.vo;

import com.ss.core.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户角色返回", description = "系统用户角色")
public class SysRoleVo extends BaseVO {

    private static final long serialVersionUID = 795527991311199425L;

    @ApiModelProperty(value = "ID", example = "1")
    private long id;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;


}
