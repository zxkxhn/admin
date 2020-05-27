package com.zxk.admin.biz.web;

import com.zxk.admin.client.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Reference
    private TestService testService;

    @PostMapping("/test")
    void test() {
        testService.aaa();
    }

}
