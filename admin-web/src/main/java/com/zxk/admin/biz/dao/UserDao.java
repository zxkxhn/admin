package com.zxk.admin.biz.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zxk.admin.biz.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * TODO
 *
 * @author xiaokun.zhang
 * Date:   2019年11月19日 16:20
 * @version 1.0
 */
public interface UserDao extends BaseMapper<User> {


    @Select("select count(name) , sum(phone) , avg(city_id) from user ${ew.customSqlSegment}")
    Map<String,Object> getAll(@Param(Constants.WRAPPER) Wrapper wrapper);



}
