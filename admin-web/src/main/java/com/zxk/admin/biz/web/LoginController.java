package com.zxk.admin.biz.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.vo.CaptchaImageVO;
import com.zxk.core.common.Result;
import com.zxk.core.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.zxk.core.config.security.constant.SecurityConstant.LOGIN_CAPTCHA_ID;

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

    @Resource
    private SysUserAo sysUserAo;

    @GetMapping("/captchaImage")
    @ApiOperation(value = "验证码图片")
    public Result<CaptchaImageVO> captchaImage(
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
        RedisUtils.getSingleton().setEx(LOGIN_CAPTCHA_ID + id, lineCaptcha.getCode(), 5, TimeUnit.MINUTES);

        CaptchaImageVO captchaImageVO = new CaptchaImageVO();
        captchaImageVO.setCaptchaImageId(id);
        captchaImageVO.setImageBase64(base64);
        // todo 验证码暴露
        if (log.isDebugEnabled()) {
            captchaImageVO.setCode(lineCaptcha.getCode());
        }
        return Result.success(captchaImageVO);
    }
}
