package com.zxk.admin.biz.web;

import com.zxk.admin.biz.ao.SysRoleAo;
import com.zxk.admin.biz.form.SysRoleAddForm;
import com.zxk.admin.biz.form.SysRoleEditForm;
import com.zxk.admin.biz.vo.SysRoleVo;
import com.ss.core.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "角色管理")
@RestController
@RequestMapping(value = "/SysRole")
public class SysRoleController {

    @Resource
    private SysRoleAo sysRoleAo;



    /**
     * 添加角色
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加角色")
    Result<Void> add(@Valid @RequestBody SysRoleAddForm sysRoleAddForm) {
        return sysRoleAo.add(sysRoleAddForm);
    }

    /**
     * 删除角色
     */
    @PostMapping("/del")
    @ApiOperation(value = "删除角色")
    Result<Void> del(long id) {
        return sysRoleAo.del(id);
    }

    /**
     * 编辑角色
     */
    @PostMapping("/edit")
    @ApiOperation(value = "编辑角色")
    Result<Void> edit(@Valid @RequestBody SysRoleEditForm sysRoleEditForm) {
        return sysRoleAo.edit(sysRoleEditForm);
    }


    /**
     * 角色列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "角色列表")
    Result<List<SysRoleVo>> list() {
        return sysRoleAo.queryAll();
    }

    /**
     * 角色详情
     */
    @PostMapping("/detail")
    @ApiOperation(value = "角色详情")
    Result<SysRoleVo> detail(long id) {
        return sysRoleAo.queryById(id);
    }
}
