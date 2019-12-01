package com.zxk.admin.biz.web;

import cn.hutool.core.bean.BeanUtil;
import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.admin.biz.form.UserForm;
import com.zxk.core.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 系统用户管理
 *
 * @author xiaokun.zhang
 * Date:   2019年11月19日 16:24
 * @version 1.0
 */
@Slf4j
@Api(tags = "系统用户管理")
@RestController
@RequestMapping(value = "/SysUser")
public class UserController {

    @Autowired
    private SysUserAo sysUserAo;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @PostMapping
    public Result add(@Valid @RequestBody UserForm userForm) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(userForm, sysUser);

        sysUserAo.addUser(sysUser);

        return Result.success();
    }



}
