package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.Table;

// default package

/**
 * BookUserRole entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Table(value="book_user_role")
public class BookUserRole implements java.io.Serializable {

	// Fields
	
	private String userid;
	private String roleid;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

}