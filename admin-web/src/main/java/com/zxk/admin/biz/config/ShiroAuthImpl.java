package com.zxk.admin.biz.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.core.config.shiro.ShiroAuth;
import com.zxk.core.config.shiro.jwt.JwtUtil;
import org.apache.shiro.authc.AccountException;
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
    public void checkLogin(Object object) {
        String token = object.toString();
        if(JwtUtil.verify(token)){
            throw new JWTVerificationException("JWT token 校验失败");
        }
        String account = JwtUtil.getClaim(token, "account");
        if(account == null){
            throw new AuthenticationException("token 解析失败");
        }
        SysUser sysUser = sysUserDao.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, account));
        if (sysUser == null) {
            throw new AccountException("用户不存在");
        }
        throw new AccountException("用户不存在");
    }
}
