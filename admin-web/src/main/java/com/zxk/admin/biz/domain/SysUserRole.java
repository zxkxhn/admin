package com.zxk.admin.biz.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private long id;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 角色ID
	 */
	private long roleId;
	
}