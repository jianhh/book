package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
import com.ithxh.baseCore.annotation.DB.Table;
// default package


/**
 * BookRole entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Table("book_role")
public class BookRole implements java.io.Serializable {

	// Fields
	@PrimaryKey("ROLEID")
	private String roleid;
	private String rolename;
	private String roleremark;
	private String roleisenable;
	private String roleisadmin;

	// Constructors

	/** default constructor */
	public BookRole() {
	}

	/** minimal constructor */
	public BookRole(String roleid, String rolename) {
		this.roleid = roleid;
		this.rolename = rolename;
	}

	// Property accessors

	public String getRoleid() {
		return this.roleid;
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