package com.zxk.admin.biz.config;

import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.core.common.Result;
import com.zxk.core.config.shiro.ShiroAuth;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 权限实现类
 *
 * @author xiaokun.zhang
 * Date:   2019年12月05日 14:07
 * @version 1.0
 */
@Service
public class ShiroAuthImpl implements ShiroAuth {

    @Resource
    private SysUserAo sysUserAo;

    @Override
    public Set<String> getRole(Object o) {
        return null;
    }

    @Override
    public Set<String> getPermissions(Object o) {
        return null;
    }

    @Override
    public Result checkLogin(Object o) {
        return Result.fail();
    }
}
