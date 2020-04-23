package com.zxk.admin.biz.web;

import com.ss.core.common.Result;
import com.zxk.admin.biz.ao.SysMenuAo;
import com.zxk.admin.biz.form.SysMenuAddForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@Api(tags = "菜单管理")
@RestController
@RequestMapping(value = "/sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuAo sysMenuAo;

    @PostMapping("/add")
    @ApiOperation(value = "添加菜单")
    Result<Void> add(@RequestBody @Valid SysMenuAddForm sysMenuAddForm) {
        return sysMenuAo.add(sysMenuAddForm);
    }
}
