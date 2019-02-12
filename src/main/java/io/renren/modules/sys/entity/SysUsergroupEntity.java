package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户组
 *
 * @author JackLee
 * @email
 * @date 2018-11-08 09:45:05
 */
@TableName("sys_usergroup")
public class SysUsergroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long ugId;
	/**
	 * 用户组名称
	 */
	@NotBlank(message="用户组名称不能为空")
	private String ugName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 部门ID
	 */
	@NotNull(message="部门不能为空")
	private Long deptId;

	/**
	 * 部门名称
	 */
	@TableField(exist=false)
	private String deptName;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 设置：
	 */
	public void setUgId(Long ugId) {
		this.ugId = ugId;
	}
	/**
	 * 获取：
	 */
	public Long getUgId() {
		return ugId;
	}
	/**
	 * 设置：用户组名称
	 */
	public void setUgName(String ugName) {
		this.ugName = ugName;
	}
	/**
	 * 获取：用户组名称
	 */
	public String getUgName() {
		return ugName;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：部门id
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门id
	 */
	public Long getDeptId() {
		return deptId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}


	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
