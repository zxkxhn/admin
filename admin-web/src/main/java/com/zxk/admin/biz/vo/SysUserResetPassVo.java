package com.zxk.admin.biz.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户列表返回对象", description = "用户接口返回对象")
public class SysUserResetPassVo {

    @ApiModelProperty(value = "用户ID", example = "1")
    private long sysUserId;

    @ApiModelProperty(value = "是否重置成功")
    private boolean isReset = false;

    @ApiModelProperty(value = "重置后密码")
    private String password;
}
