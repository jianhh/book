package com.ithxh.xhh.vo.formbean;

import java.util.List;

// default package


/**
 * BookRole entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BookRoleVo implements java.io.Serializable {

	// Fields

	private String roleid;
	private String rolename;
	private String roleremark;
	private String roleisenable;
	private String roleisadmin;
	
	private List<BookPrivilegeVo> list;

	// Constructors

	/** default constructor */
	public BookRoleVo() {
	}

	/** minimal constructor */
	public BookRoleVo(String roleid, String rolename) {
		this.roleid = roleid;
		this.rolename = rolename;
	}

	// Property accessors

	public String getRoleid() {
		return this.roleid;
	}

	public List<BookPrivilegeVo> getList() {
		return list;
	}

	public void setList(List<BookPrivilegeVo> list) {
		this.list = list;
	}

	public String getRoleisenable() {
		return roleisenable;
	}

	public void setRoleisenable(String roleisenable) {
		this.roleisenable = roleisenable;
	}

	public String getRoleisadmin() {
		return roleisadmin;
	}

	public void setRoleisadmin(String roleisadmin) {
		this.roleisadmin = roleisadmin;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoleremark() {
		return this.roleremark;
	}

	public void setRoleremark(String roleremark) {
		this.roleremark = roleremark;
	}
}