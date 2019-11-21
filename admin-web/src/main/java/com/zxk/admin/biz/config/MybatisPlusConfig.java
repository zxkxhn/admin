package com.zxk.admin.biz.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zxk.core.filter.xss.SQLFilter;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author xiaokun.zhang
 * Date:   2019年11月19日 15:56
 * @version 1.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.zxk.admin.biz.dao")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置最大单页限制数量，默认 50 条，-1 不受限制
        paginationInterceptor.setLimit(50);
        return paginationInterceptor;
    }


}
