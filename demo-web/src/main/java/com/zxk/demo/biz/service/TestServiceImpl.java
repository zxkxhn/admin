package com.zxk.demo.biz.service;

import com.zxk.admin.client.service.TestService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void aaa() {
        System.out.println("1111111111111");
    }
}
