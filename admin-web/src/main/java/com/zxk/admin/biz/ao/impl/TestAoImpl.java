package com.zxk.admin.biz.ao.impl;

import com.zxk.admin.biz.ao.TestAo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author xiaokun.zhang
 * Date:   2019年11月28日 15:51
 * @version 1.0
 */

@Service
public class TestAoImpl implements TestAo {


    @Override
    @Cacheable(value = "mycache")
    public String test() {
        return "66666666666666666666666";
    }
}
