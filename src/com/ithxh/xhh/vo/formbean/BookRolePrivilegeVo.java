package com.ithxh.xhh.vo.formbean;
// default package

/**
 * BookRolePrivilege entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BookRolePrivilegeVo implements java.io.Serializable {

	// Fields

	private String roleid;
	private String privilegeid;

	// Constructors

	/** default constructor */
	public BookRolePrivilegeVo() {
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