package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 用户组角色对应关系
 *
 * @author JackLee
 * @email
 * @date 2018-11-08 10:01:47
 */
@TableName("sys_usergroup_role")
public class SysUsergroupRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 用户组id
	 */
	private Long ugId;
	/**
	 * 用户id
	 */
	private Long roleId;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户组id
	 */
	public void setUgId(Long ugId) {
		this.ugId = ugId;
	}
	/**
	 * 获取：用户组id
	 */
	public Long getUgId() {
		return ugId;
	}
	/**
	 * 设置：用户id
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：用户id
	 */
	public Long getRoleId() {
		return roleId;
	}
}
