package com.zxk.admin.biz.ao.impl;

import com.zxk.admin.biz.ao.SysUserAo;
import com.zxk.admin.biz.dao.SysUserDao;
import com.zxk.admin.biz.domain.SysUser;
import com.zxk.admin.biz.enums.SysUserStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户管理
 *
 * @author zhangxk
 * @Email 980137428@qq.com
 * Date:   2019年12月01日 11:40
 */
@Service
public class SysUserAoImpl implements SysUserAo {


    @Resource
    private SysUserDao sysUserDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(SysUser sysUser) {

        sysUser.setStatus(SysUserStatusEnum.normal.getValue());
        sysUserDao.insert(sysUser);
    }
}
