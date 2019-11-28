package com.zxk.admin.biz.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxk.admin.biz.dao.AddressDao;
import com.zxk.admin.biz.dao.UserDao;
import com.zxk.admin.biz.domain.Address;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * TODO
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
    private UserDao userDao;

    @Resource
    private AddressDao addressDao;

    @GetMapping("/")
    @ApiOperation(value = "获取用户列表")
    public List<Address> getUserList() {
        Page<Address> page = new Page<>(1,2);
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        IPage<Address> iPage = addressDao.selectPage(page, queryWrapper);
        System.out.println(iPage);
        return iPage.getRecords();
    }


}
