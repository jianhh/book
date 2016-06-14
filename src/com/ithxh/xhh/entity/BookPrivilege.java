package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
import com.ithxh.baseCore.annotation.DB.Table;
// default package


/**
 * BookPrivilege entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Table("book_privilege")
public class BookPrivilege implements java.io.Serializable {

	// Fields
	@PrimaryKey("PRIVILEGEID")
	private String privilegeid;
	private String privilegename;
	private String privilegeurl;
	private String privilegedesc;
	private String privilegeicon;
	private String privilegefatherid;
	private Short privilegelevel;
	private String privilegeisenable;
	private String privilegeflag;

	// Constructors

	/** default constructor */
	public BookPrivilege() {
	}

	/** minimal constructor */
	public BookPrivilege(String privilegeid, String privilegename,
			String privilegeurl) {
		this.privilegeid = privilegeid;
		this.privilegename = privilegename;
		this.privilegeurl = privilegeurl;
	}

	/** full constructor */
	public BookPrivilege(String privilegeid, String privilegename,
			String privilegeurl, String privilegedesc) {
		this.privilegeid = privilegeid;
		this.privilegename = privilegename;
		this.privilegeurl = privilegeurl;
		this.privilegedesc = privilegedesc;
	}

	// Property accessors

	public String getPrivilegeid() {
		return this.privilegeid;
	}

	public String getPrivilegeicon() {
		return privilegeicon;
	}

	public void setPrivilegeicon(String privilegeicon) {
		this.privilegeicon = privilegeicon;
	}

	public String getPrivilegefatherid() {
		return privilegefatherid;
	}

	public void setPrivilegefatherid(String privilegefatherid) {
		this.privilegefatherid = privilegefatherid;
	}

	public Short getPrivilegelevel() {
		return privilegelevel;
	}

	public void setPrivilegelevel(Short privilegelevel) {
		this.privilegelevel = privilegelevel;
	}

	public String getPrivilegeisenable() {
		return privilegeisenable;
	}

	public void setPrivilegeisenable(String privilegeisenable) {
		this.privilegeisenable = privilegeisenable;
	}

	public String getPrivilegeflag() {
		return privilegeflag;
	}

	public void setPrivilegeflag(String privilegeflag) {
		this.privilegeflag = privilegeflag;
	}

	public void setPrivilegeid(String privilegeid) {
		this.privilegeid = privilegeid;
	}

	public String getPrivilegename() {
		return this.privilegename;
	}

	public void setPrivilegename(String privilegename) {
		this.privilegename = privilegename;
	}

	public String getPrivilegeurl() {
		return this.privilegeurl;
	}

	public void setPrivilegeurl(String privilegeurl) {
		this.privilegeurl = privilegeurl;
	}

	public String getPrivilegedesc() {
		return this.privilegedesc;
	}

	public void setPrivilegedesc(String privilegedesc) {
		this.privilegedesc = privilegedesc;
	}
}