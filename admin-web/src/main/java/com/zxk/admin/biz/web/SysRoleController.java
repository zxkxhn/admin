package com.zxk.admin.biz.web;

import com.zxk.admin.biz.form.SysRoleAddForm;
import com.zxk.admin.biz.form.SysUserEditForm;
import com.zxk.core.common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/SysRole")
public class SysRoleController {



    /**
     * 添加角色
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加角色")
    Result<Void> add(@Valid @RequestBody SysRoleAddForm sysRoleAddForm) {
        return Result.success();
    }

    /**
     * 删除角色
     */
    @PostMapping("/del")
    @ApiOperation(value = "删除角色")
    Result<Void> del(long id) {
        return Result.success();
    }

    /**
     * 编辑角色
     */
    @PostMapping("/edit")
    @ApiOperation(value = "编辑角色")
    Result<Void> edit(@Valid @RequestBody SysUserEditForm sysUserEditForm) {
        return Result.success();
    }


    /**
     * 角色列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "角色列表")
    Result<Void> list() {
        return Result.success();
    }

    /**
     * 角色详情
     */
    @PostMapping("/del")
    @ApiOperation(value = "角色详情")
    Result<Void> query(long id) {
        return Result.success();
    }
}
