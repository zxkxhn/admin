package com.zxk.admin;

import com.zxk.core.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminTest {

    @Test
    public void aaa() {
        RedisUtils.getSingleton().set("aaaa", 1111);
        System.out.println( RedisUtils.getSingleton().get("aaaa"));
    }
}
