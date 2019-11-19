package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author xiaokun.zhang
 * Date:   2019年11月19日 16:19
 * @version 1.0
 */

@ApiModel(description="用户实体")
@Data
@TableName("user")
public class User {

    @ApiModelProperty("用户编号")
    private int id;
    @ApiModelProperty("用户名称")
    private String name;
    @ApiModelProperty("用户手机号")
    private String phone;

}

