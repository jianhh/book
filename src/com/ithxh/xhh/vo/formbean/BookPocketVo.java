package com.ithxh.xhh.vo.formbean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookPocketVo implements Serializable{

	private String pocketid;
	private String userid;
	private String username;
	private String sellid;
	private String bookname;
	private String addtime;
	private int booknum;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getPocketid() {
		return pocketid;
	}
	public void setPocketid(String pocketid) {
		this.pocketid = pocketid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getSellid() {
		return sellid;
	}
	public void setSellid(String sellid) {
		this.sellid = sellid;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public int getBooknum() {
		return booknum;
	}
	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}
	
}
