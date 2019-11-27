package com.zxk.admin;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxk.admin.biz.dao.AddressDao;
import com.zxk.admin.biz.dao.UserDao;
import com.zxk.admin.biz.domain.Address;
import com.zxk.admin.biz.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AdminTest{

    @Resource
    private UserDao userDao;
    @Resource
    private AddressDao addressDao;

    @Test
    void test_01() {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setCityId(RandomUtil.randomInt(1,5000));
            user.setCreateTime(DateUtil.date());
            user.setName(RandomUtil.randomString(RandomUtil.randomInt(5)));
            user.setPassword(SecureUtil.md5(RandomUtil.randomString(RandomUtil.randomInt(5))));
            user.setPhone(RandomUtil.randomInt(13) + "");
            userDao.insert(user);
        }

    }

    @Test
    void test_02(){
        System.out.println(JSONObject.toJSONString(userDao.selectById(406188693116682241L)));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getName, "ylm5");
        System.out.println(JSONObject.toJSONString(userDao.selectList(queryWrapper)));

    }

    @Test
    void test_03(){
        Address address = new Address();
        address.setCode(RandomUtil.randomString(32));
        address.setLit(RandomUtil.randomInt(10));
        address.setType(RandomUtil.randomInt(10));
        address.setName(RandomUtil.randomString(32));
        address.setPid(RandomUtil.randomString(32));
        addressDao.insert(address);


        System.out.println(JSONObject.toJSONString(addressDao.selectList(new QueryWrapper<>())));
    }



}
