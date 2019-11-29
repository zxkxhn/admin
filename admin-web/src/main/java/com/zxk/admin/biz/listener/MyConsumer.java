package com.zxk.admin.biz.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "boot_producer_group")
public class MyConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("received message: " + message);
    }
}
