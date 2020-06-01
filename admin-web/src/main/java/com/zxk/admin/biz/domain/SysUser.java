package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ss.core.common.BaseDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_user")
public class SysUser extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 用户性别
     */
    private String gender;

    /**
     * 头像真实名称
     */
    private String avatarName;

    /**
     * 头像存储的路径
     */
    private String avatarPath;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否启用
     */
    private boolean enabled;

    /**
     * 是否为admin账号
     */
    private boolean isAdmin = false;
}