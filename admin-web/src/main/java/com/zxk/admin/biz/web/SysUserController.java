package com.zxk.admin.biz.web;

import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.form.SysUserAddForm;
import com.zxk.admin.biz.query.SysUserQuery;
import com.zxk.admin.biz.vo.sysuser.SysUserVO;
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
@RequestMapping(value = "/SysUser")
public class SysUserController {

    @Resource
    private SysUserAo sysUserAo;

    @GetMapping("/page")
    @ApiOperation(value = "用户列表")
    public Result<PageVO<SysUserVO>> queryPage(SysUserQuery sysUserQuery) {
        return sysUserAo.selectList();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "用户")
    public Result<SysUserVO> query(@PathVariable @ApiParam(name = "id", value = "用户ID", example = "1") long id) {
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    public Result<Void> delete(@PathVariable @ApiParam(name = "id", value = "用户ID", example = "1") long id) {
        return Result.success();
    }

    @PostMapping("add")
    @ApiOperation(value = "添加用户")
    public Result<Void> add(@Valid @RequestBody SysUserAddForm sysUserAddForm) {
        return sysUserAo.addUser(sysUserAddForm);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "修改用户")
    public Result<Void> update(@PathVariable @ApiParam(name = "id", value = "用户ID", example = "1") long id,
                               @Valid @RequestBody SysUserAddForm sysUserAddForm) {
        return sysUserAo.addUser(sysUserAddForm);
    }

}
