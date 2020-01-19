package com.zxk.admin.biz.web;

import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.form.SysUserAddForm;
import com.zxk.core.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * 用户管理
 *
 * @author xiaokun.zhang
 * Date:   2019年11月19日 16:24
 * @version 1.0
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private SysUserAo sysUserAo;

    @GetMapping("selectList")
    @ApiOperation(value = "用户列表")
    public Result selectList() {
        return sysUserAo.selectList();
    }

    @PostMapping("addUser")
    @ApiOperation(value = "添加用户")
    public Result addUser(@Valid @RequestBody SysUserAddForm sysUserAddForm) {
        return sysUserAo.addUser(sysUserAddForm);
    }

}
