package com.zxk.admin.biz.web;

import com.zxk.admin.biz.form.UserForm;
import com.zxk.core.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

    private ExecutorService executors = Executors.newFixedThreadPool(5);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(name = "userForm", value = "新增用户form表单", required = true, dataType = "UserForm")
    @PostMapping
    public Result add(@Valid @RequestBody UserForm userForm) {
        log.debug("name:{}", userForm);

        return Result.success(userForm);
    }



    @GetMapping
    public void put(){
        for (int i = 0; i < 50000; i++) {
            final int i1 = i;
            executors.execute(() -> rocketMQTemplate.syncSend("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message   num:" + i1).build()));
        }

    }

}
