//package com.zxk.admin.biz.config;
//
//import cn.hutool.core.collection.CollectionUtil;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.ss.core.util.RedisUtils;
//import com.ss.security.constant.SecurityConstant;
//import com.ss.security.service.SecurityService;
//import com.zxk.admin.biz.dao.*;
//import com.zxk.admin.biz.domain.SysMenu;
//import com.zxk.admin.biz.domain.SysRoleMenu;
//import com.zxk.admin.biz.domain.SysUser;
//import com.zxk.admin.biz.domain.SysUserRole;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class SecurityServiceImpl implements SecurityService {
//
//    @Resource
//    private SysUserDao sysUserDao;
//
//    @Resource
//    private SysRoleDao sysRoleDao;
//
//    @Resource
//    private SysRoleMenuDao sysRoleMenuDao;
//
//    @Resource
//    private SysUserRoleDao sysUserRoleDao;
//
//    @Resource
//    private SysMenuDao sysMenuDao;
//
//
//    @Override
//    public UserDetails getUserDetail(String username) {
//        SysUser sysUser = sysUserDao.selectOne(new LambdaQueryWrapper<SysUser>()
//                .eq(SysUser::getUsername, username));
//        if (sysUser == null) {
//            throw new UsernameNotFoundException("当前账号不存在!");
//        }
//        return new UserDetails() {
//            private static final long serialVersionUID = -7691160220825068752L;
//
//
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return getCurrUserPerms(username);
//            }
//
//            @Override
//            public String getPassword() {
//                return sysUser.getPassword();
//            }
//
//            @Override
//            public String getUsername() {
//                return sysUser.getUsername();
//            }
//
//            /**
//             * 账户是否过期
//             *
//             */
//            @Override
//            public boolean isAccountNonExpired() {
//                return true;
//            }
//
//            /**
//             * 是否禁用
//             *
//             */
//            @Override
//            public boolean isAccountNonLocked() {
//                return true;
//            }
//
//            /**
//             * 密码是否过期
//             *
//             */
//            @Override
//            public boolean isCredentialsNonExpired() {
//                return true;
//            }
//
//            /**
//             * 是否启用
//             *
//             */
//            @Override
//            public boolean isEnabled() {
//                return sysUser.getStatus() == 1;
//            }
//        };
//    }
//
//    @Override
//    public List<GrantedAuthority> getCurrUserPerms(String username) {
//        SysUser sysUser = sysUserDao.selectOne(new LambdaQueryWrapper<SysUser>()
//                .eq(SysUser::getUsername, username));
//        if (sysUser == null) {
//            throw new UsernameNotFoundException("当前账号不存在!");
//        }
//        List<GrantedAuthority> authorityList = new ArrayList<>();
//
//        long id = sysUser.getId();
//        List<Long> roleIds = sysUserRoleDao.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, id))
//                .stream().map(SysUserRole::getId).collect(Collectors.toList()
//                );
//        if (CollectionUtil.isEmpty(roleIds)) {
//            return new ArrayList<>();
//        }
//
//        List<Long> menuIds = sysRoleMenuDao.selectList(new LambdaQueryWrapper<SysRoleMenu>().in(SysRoleMenu::getRoleId, roleIds)).stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
//        List<SysMenu> menuList = sysMenuDao.selectList(new LambdaQueryWrapper<SysMenu>().in(SysMenu::getId, menuIds));
//
//        menuList.forEach(sysMenu -> {
//            String perms = sysMenu.getPerms();
//            authorityList.add(new SimpleGrantedAuthority(perms));
//        });
//        return authorityList;
//    }
//
//    @Override
//    public void clearUserToken(String username) {
//        SysUser sysUser = sysUserDao.selectOne(new LambdaQueryWrapper<SysUser>()
//                .eq(SysUser::getUsername, username));
//        if (sysUser == null) {
//            throw new UsernameNotFoundException("当前账号不存在!");
//        }
//
//        if (!RedisUtils.getSingleton().hasKey(SecurityConstant.USER_TOKEN + username)) {
//            return;
//        }
//        String token = (String) RedisUtils.getSingleton().get(SecurityConstant.USER_TOKEN + username);
//        if (!RedisUtils.getSingleton().hasKey(SecurityConstant.TOKEN_PRE + token)) {
//            return;
//        }
//        RedisUtils.getSingleton().delete(SecurityConstant.USER_TOKEN + username);
//        RedisUtils.getSingleton().delete(SecurityConstant.TOKEN_PRE + token);
//    }
//}
