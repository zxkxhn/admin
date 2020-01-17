package com.zxk.admin;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.dao.TestDao;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.core.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class AdminTest {


    @Resource
    private TestDao testDao;

    private ExecutorService pool = Executors.newFixedThreadPool(2);

    @Test
    public void aaa() {
        for (int i = 0; i < 50000; i++) {
            com.zxk.admin.biz.domain.Test test = new com.zxk.admin.biz.domain.Test();
            testDao.insert(test);
        }
    }

    @Test
    public void bbb() throws InterruptedException {

        pool.submit(() -> {
            QueryWrapper<com.zxk.admin.biz.domain.Test> queryWrapper = new QueryWrapper<>();
            for (int i = 1; i < 5000; i++) {
                Page<com.zxk.admin.biz.domain.Test> page = new Page<>(i, 20);
                IPage<com.zxk.admin.biz.domain.Test> testList = testDao.selectPage(page, queryWrapper);
                System.out.println(JSONObject.toJSONString(testList));
            }
        });

        pool.submit(() -> {
            QueryWrapper<com.zxk.admin.biz.domain.Test> queryWrapper = new QueryWrapper<>();
            for (int i = 1; i < 5000; i++) {
                Page<com.zxk.admin.biz.domain.Test> page = new Page<>(i, 20);
                IPage<com.zxk.admin.biz.domain.Test> testList = testDao.selectPage(page, queryWrapper);
                System.out.println(JSONObject.toJSONString(testList));
            }
        });

        pool.submit(() -> {
            QueryWrapper<com.zxk.admin.biz.domain.Test> queryWrapper = new QueryWrapper<>();
            for (int i = 1; i < 5000; i++) {
                Page<com.zxk.admin.biz.domain.Test> page = new Page<>(i, 20);
                IPage<com.zxk.admin.biz.domain.Test> testList = testDao.selectPage(page, queryWrapper);
                System.out.println(JSONObject.toJSONString(testList));
            }
        });


        Thread.sleep(500000);

    }

}
