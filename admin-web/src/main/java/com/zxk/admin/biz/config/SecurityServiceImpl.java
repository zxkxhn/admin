package com.zxk.admin.biz.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zxk.admin.biz.dao.*;
import com.zxk.admin.biz.domain.SysMenu;
import com.zxk.admin.biz.domain.SysRoleMenu;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.admin.biz.domain.SysUserRole;
import com.zxk.core.config.security.service.SecurityService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Resource
    private SysMenuDao sysMenuDao;


    @Override
    public UserDetails getUserDetail(String username) {
        SysUser sysUser = sysUserDao.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));
        if (sysUser == null) {
            throw new UsernameNotFoundException("当前账号不存在!");
        }
        return new UserDetails() {

            private static final long serialVersionUID = -7691160220825068752L;

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorityList = new ArrayList<>();

                long id = sysUser.getId();
                List<Long> roleIds = sysUserRoleDao.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, id))
                        .stream().map(SysUserRole::getId).collect(Collectors.toList()
                        );
                List<Long> menuIds = sysRoleMenuDao.selectList(new LambdaQueryWrapper<SysRoleMenu>().in(SysRoleMenu::getRoleId, roleIds)).stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
                List<SysMenu> menuList = sysMenuDao.selectList(new LambdaQueryWrapper<SysMenu>().in(SysMenu::getId, menuIds));

                menuList.forEach(sysMenu -> {
                    String perms = sysMenu.getPerms();
                    authorityList.add(new SimpleGrantedAuthority(perms));
                });
                return authorityList;
            }

            @Override
            public String getPassword() {
                return sysUser.getPassword();
            }

            @Override
            public String getUsername() {
                return sysUser.getUsername();
            }

            /**
             * 账户是否过期
             *
             * @return
             */
            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return sysUser.getStatus() == 1;
            }
        };
    }
}
