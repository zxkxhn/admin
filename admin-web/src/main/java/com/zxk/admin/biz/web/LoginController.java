package com.zxk.admin.biz.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.config.RedisConstant;
import com.zxk.admin.biz.form.login.SysUserAccountLoginForm;
import com.zxk.admin.biz.form.login.SysUserMobileLoginForm;
import com.zxk.admin.biz.vo.sysuserlogin.CaptchaImageVO;
import com.zxk.core.common.Result;
import com.zxk.core.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
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
@Api(tags = "登录")
public class LoginController {

    @Resource
    private SysUserAo sysUserAo;

    @GetMapping("/captchaImage")
    @ApiOperation(value = "验证码图片")
    public Result<CaptchaImageVO> captchaImage(
            @ApiParam(name = "width", value = "验证码宽度") Integer width,
            @ApiParam(name = "height", value = "验证码高度") Integer height
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
        RedisUtils.getSingleton().setEx(RedisConstant.CAPTCHA_IMAGE_ID + id, lineCaptcha.getCode(),RedisConstant.CAPTCHA_IMAGE_EXPIRED, TimeUnit.MINUTES);
        CaptchaImageVO captchaImageVO = new CaptchaImageVO();
        captchaImageVO.setCaptchaImageId(id);
        captchaImageVO.setImageBase64(base64);
        return Result.success(captchaImageVO);
    }

    @PostMapping("/login")
    @ApiOperation(value = "账号登陆登录")
    public Result<String> login(@RequestBody @Valid SysUserAccountLoginForm sysUserLoginForm) {
        return sysUserAo.login(sysUserLoginForm);
    }

    @PostMapping("/login2")
    @ApiOperation(value = "手机号登陆")
    public Result<String> login(@RequestBody @Valid SysUserMobileLoginForm sysUserMobileLoginForm) {
        return sysUserAo.login(sysUserMobileLoginForm);
    }

}
