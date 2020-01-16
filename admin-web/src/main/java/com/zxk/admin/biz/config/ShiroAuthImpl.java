package com.zxk.admin.biz.config;

import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.core.config.shiro.ShiroAuth;
import com.zxk.core.config.shiro.jwt.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
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
    private SysUserDao sysUserDao;


    @Override
    public Set<String> getRole(PrincipalCollection principals) {
        return new HashSet<>();
    }

    @Override
    public Set<String> getPermissions(PrincipalCollection principals) {
        return new HashSet<>();
    }

    @Override
    public void checkLogin(Object o) {
        throw new AuthenticationException("token 解析失败");
    }

    @Override
    public void RefreshToken(Object o) {

    }
}
