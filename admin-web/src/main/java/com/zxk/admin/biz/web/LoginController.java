package com.zxk.admin.biz.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.ss.core.common.Result;
import com.ss.core.util.RedisUtils;
import com.ss.security.constant.SecurityConstant;
import com.zxk.admin.biz.vo.CaptchaImageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 登录模块
 *
 * @author xiaokun.zhang
 * Date:   2019年11月19日 16:24
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/login")
@Api(tags = "登录")
public class LoginController {


    @GetMapping("/captchaImage")
    @ApiOperation(value = "验证码图片")
    public Result<CaptchaImageVo> captchaImage(
            @ApiParam(name = "width", value = "验证码宽度", example = "300") Integer width,
            @ApiParam(name = "height", value = "验证码高度", example = "100") Integer height
    ) {
        String id = IdUtil.fastSimpleUUID();
        LineCaptcha lineCaptcha;
        if (width == null || height == null) {
            //定义图形验证码的长和宽
            lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        } else {
            lineCaptcha = CaptchaUtil.createLineCaptcha(width, height);
        }
        String base64 = lineCaptcha.getImageBase64();
        // 加入redis 5 分钟过期
        RedisUtils.getSingleton().setEx(SecurityConstant.LOGIN_CAPTCHA_ID + id, lineCaptcha.getCode(), 5, TimeUnit.MINUTES);

        CaptchaImageVo captchaImageVO = new CaptchaImageVo();
        captchaImageVO.setCaptchaImageId(id);
        captchaImageVO.setImageBase64(base64);
        // todo 验证码暴露
        if (log.isDebugEnabled()) {
            captchaImageVO.setCode(lineCaptcha.getCode());
        }
        return Result.success(captchaImageVO);
    }
}
