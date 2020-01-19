package com.zxk.admin.biz.web;

import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.form.SysUserLoginForm;
import com.zxk.core.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录模块
 *
 * @author xiaokun.zhang
 * Date:   2019年11月19日 16:24
 * @version 1.0
 */
@Slf4j
@Api(tags = "登录管理")
@RestController
public class LoginController {

    @Resource
    private SysUserAo sysUserAo;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result login(SysUserLoginForm sysUserLoginForm) {
        return sysUserAo.login(sysUserLoginForm);
    }

}
