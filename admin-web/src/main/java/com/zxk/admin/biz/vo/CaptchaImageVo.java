package com.zxk.admin.biz.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zxk
 */
@Data
@ApiModel(value = "验证码返回对象", description = "接口返回对象")
public class CaptchaImageVo implements Serializable {

    private static final long serialVersionUID = -5001674739388258997L;
    @ApiModelProperty(value = "验证码ID")
    private String captchaImageId;

    @ApiModelProperty(value = "验证码 base64 图片")
    private String imageBase64;

    @ApiModelProperty(value = "验证码 code")
    private String code;

}
