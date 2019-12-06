package com.zxk.admin.biz.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.admin.biz.enums.SysUserStatusEnum;
import com.zxk.core.config.shiro.JwtUtil;
import com.zxk.core.config.shiro.ShiroAuth;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
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
        String token = o.toString();
        SysUser sysUser = (SysUser) JwtUtil.getUser(token, SysUser.class);
        if(sysUser == null){
            throw new AccountException("token 解析失败");
        }
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(SysUser::getUsername, sysUser.getUsername())
                .eq(SysUser::getPassword, sysUser.getPassword())
                .eq(SysUser::getSalt, sysUser.getSalt())
        ;
        sysUser = sysUserDao.selectOne(queryWrapper);
        if (sysUser == null) {
            throw new UnknownAccountException("未知账户");
        }
        if(sysUser.getStatus() == SysUserStatusEnum.Frozen.getValue()){
            throw new DisabledAccountException("该账号已被冻结");
        }
    }
}
