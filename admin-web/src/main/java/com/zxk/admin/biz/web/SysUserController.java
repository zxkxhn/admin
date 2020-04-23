package com.zxk.admin.biz.web;

import com.ss.core.common.PageVO;
import com.ss.core.common.Result;
import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.form.SysUserAddForm;
import com.zxk.admin.biz.form.SysUserEditForm;
import com.zxk.admin.biz.query.SysUserQuery;
import com.zxk.admin.biz.vo.SysUserVo;
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
        return sysUserAo.add(sysUserAddForm);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除用户")
    public Result<Void> delete(@RequestBody @ApiParam(name = "id", value = "用户ID", example = "1") long id) {
        return sysUserAo.del(id);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "修改用户")
    public Result<Void> edit(@Valid @RequestBody SysUserEditForm sysUserEditForm) {
        return sysUserAo.update(sysUserEditForm);
    }

    @GetMapping("/page")
    @ApiOperation(value = "用户列表")
    public Result<PageVO<SysUserVo>> queryPage(SysUserQuery sysUserQuery) {
        return sysUserAo.queryPage(sysUserQuery);
    }


    @GetMapping("/query")
    @ApiOperation(value = "用户")
    public Result<SysUserVo> query(@RequestBody @ApiParam(name = "id", value = "用户ID", example = "1") long id) {
        return Result.success();
    }


    @PostMapping(value = "/resetPass")
    @ApiOperation(value = "重置密码")
    public Result<String> resetPass(@RequestParam @ApiParam(name = "id", value = "用户ID", example = "1") long id) {
        return sysUserAo.resetPass(id);
    }

    @PostMapping(value = "/updatePassword")
    @ApiOperation(value = "重置密码")
    public Result<Void> updatePassword(@RequestParam
                                       @ApiParam(name = "id", value = "用户ID", example = "1") long id,
                                       @ApiParam(name = "password", value = "密码") String password,
                                       @ApiParam(name = "newPassword", value = "新密码") String newPassword
    ) {
        return sysUserAo.updatePassword(id, password, newPassword);
    }

}
