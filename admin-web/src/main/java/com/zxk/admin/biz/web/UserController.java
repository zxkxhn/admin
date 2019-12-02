package com.zxk.admin.biz.web;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




    @GetMapping
    public void put(){
        for (int i = 0; i < 50000; i++) {
            final int i1 = i;
            executors.execute(() -> rocketMQTemplate.syncSend("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message   num:" + i1).build()));
        }

    }

}
