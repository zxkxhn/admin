package com.zxk.admin.biz.web;

import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.form.SysUserAddForm;
import com.zxk.admin.biz.query.SysUserQuery;
import com.zxk.admin.biz.vo.SysUserVO;
import com.zxk.core.common.PageVO;
import com.zxk.core.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping(value = "/sysUser")
public class SysUserController {

    @Resource
    private SysUserAo sysUserAo;


    @PostMapping("/add")
    @ApiOperation(value = "添加用户")
    public Result<Void> add(@Valid @RequestBody SysUserAddForm sysUserAddForm) {
        return sysUserAo.addUser(sysUserAddForm);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除用户")
    public Result<Void> delete(@RequestBody @ApiParam(name = "id", value = "用户ID", example = "1") long id) {
        return Result.success();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改用户")
    public Result<Void> update(@Valid @RequestBody SysUserAddForm sysUserAddForm) {
        return sysUserAo.addUser(sysUserAddForm);
    }

    @GetMapping("/page")
    @ApiOperation(value = "用户列表")
    public Result<PageVO<SysUserVO>> queryPage(SysUserQuery sysUserQuery) {
        return sysUserAo.selectList(sysUserQuery);
    }


    @GetMapping("/query")
    @ApiOperation(value = "用户")
    public Result<SysUserVO> query(@RequestBody @ApiParam(name = "id", value = "用户ID", example = "1") long id) {
        return Result.success();
    }


    @PostMapping(value = "/resetPass")
    @ApiOperation(value = "重置密码")
    public Result<String> resetPass(@RequestParam @ApiParam(name = "id", value = "用户ID", example = "1") long id) {
        return sysUserAo.resetPass(id);
    }

}
