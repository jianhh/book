package com.ithxh.xhh.vo.formbean;

import java.util.List;

// default package


/**
 * BookPrivilege entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BookPrivilegeVo implements java.io.Serializable {

	// Fields

	private String privilegeid;
	private String privilegename;
	private String privilegeurl;
	private String privilegedesc;
	private String privilegeicon;
	private String privilegefatherid;
	private Short privilegelevel;
	private String privilegeisenable;
	private String privilegeflag;
	
	private List<BookPrivilegeVo> list;


	// Constructors

	/** default constructor */
	public BookPrivilegeVo() {
	}

	/** minimal constructor */
	public BookPrivilegeVo(String privilegeid, String privilegename,
			String privilegeurl) {
		this.privilegeid = privilegeid;
		this.privilegename = privilegename;
		this.privilegeurl = privilegeurl;
	}

	/** full constructor */
	public BookPrivilegeVo(String privilegeid, String privilegename,
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

	public List<BookPrivilegeVo> getList() {
		return list;
	}

	public void setList(List<BookPrivilegeVo> list) {
		this.list = list;
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