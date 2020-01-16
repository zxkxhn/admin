package com.zxk.admin;

import cn.hutool.core.util.RandomUtil;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.core.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AdminTest {


    @Resource
    private SysUserDao sysUserDao;

    @Test
    public void aaa() {
        for (int i = 0; i < 5000; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setSalt(RandomUtil.randomString(20));
            sysUser.setUsername(RandomUtil.randomString(20));
            sysUser.setPassword(RandomUtil.randomString(20));
            sysUser.setEmail(RandomUtil.randomString(20));
            sysUser.setMobile(RandomUtil.randomNumbers(11));
            sysUser.setStatus(RandomUtil.randomInt(10));
            sysUserDao.insert(sysUser);
        }


    }
}
