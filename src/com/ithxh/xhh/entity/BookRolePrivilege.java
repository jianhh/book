package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.Table;
// default package

/**
 * BookRolePrivilege entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Table("book_role_privilege")
public class BookRolePrivilege implements java.io.Serializable {

	// Fields
	
	private String roleid;
	private String privilegeid;

	// Constructors

	/** default constructor */
	public BookRolePrivilege() {
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getPrivilegeid() {
		return privilegeid;
	}

	public void setPrivilegeid(String privilegeid) {
		this.privilegeid = privilegeid;
	}
}