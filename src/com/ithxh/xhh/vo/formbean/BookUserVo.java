package com.ithxh.xhh.vo.formbean;

import java.util.List;
// default package


/**
 * BookUser entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BookUserVo implements java.io.Serializable {

	// Fields

	private String userid;
	private String username;
	private String password;
	private String email;
	private String birthday;
	private String sex;
	private String isenable;
	private String lastlogintime;
	private Long logintimes;
	private String address;
	private String isqq;
	private String userpic;
	private String userpicpath;
	private String userlastloginip;
	
	private BookRoleVo roleVo;
	private String registertime;
	private String phonenumber;
	private String shortphonenumber;
	private String schoolid;
	private String schoolname;
	private String collegeid;
	private String collegename;
	private String isactive;
	
	private String confirmpassword;
	
	private String captcha;
	
	private List<BookPrivilegeVo> pList;//该用户的权限列表，未封装的
	
	private List<BookPrivilegeVo> listPrivilegeVo; // 存放能被访问的栏目,拼装过后的

	// Constructors

	/** default constructor */
	public BookUserVo() {
	}


	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public List<BookPrivilegeVo> getpList() {
		return pList;
	}


	public void setpList(List<BookPrivilegeVo> pList) {
		this.pList = pList;
	}


	public String getSchoolname() {
		return schoolname;
	}


	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}


	public String getCollegename() {
		return collegename;
	}


	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}


	public String getUserpicpath() {
		return userpicpath;
	}


	public void setUserpicpath(String userpicpath) {
		this.userpicpath = userpicpath;
	}


	public String getCaptcha() {
		return captcha;
	}


	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}


	public String getConfirmpassword() {
		return confirmpassword;
	}


	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}


	public String getIsactive() {
		return isactive;
	}


	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getShortphonenumber() {
		return shortphonenumber;
	}


	public void setShortphonenumber(String shortphonenumber) {
		this.shortphonenumber = shortphonenumber;
	}


	public String getSchoolid() {
		return schoolid;
	}


	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}


	public String getCollegeid() {
		return collegeid;
	}


	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}


	public String getRegistertime() {
		return registertime;
	}


	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}


	public BookRoleVo getRoleVo() {
		return roleVo;
	}


	public void setRoleVo(BookRoleVo roleVo) {
		this.roleVo = roleVo;
	}


	public List<BookPrivilegeVo> getListPrivilegeVo() {
		return listPrivilegeVo;
	}


	public void setListPrivilegeVo(List<BookPrivilegeVo> listPrivilegeVo) {
		this.listPrivilegeVo = listPrivilegeVo;
	}


	public String getUserlastloginip() {
		return userlastloginip;
	}


	public void setUserlastloginip(String userlastloginip) {
		this.userlastloginip = userlastloginip;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIsenable() {
		return this.isenable;
	}

	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}

	public String getLastlogintime() {
		return this.lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public Long getLogintimes() {
		return this.logintimes;
	}

	public void setLogintimes(Long logintimes) {
		this.logintimes = logintimes;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsqq() {
		return this.isqq;
	}

	public void setIsqq(String isqq) {
		this.isqq = isqq;
	}

	public String getUserpic() {
		return this.userpic;
	}

	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}

}