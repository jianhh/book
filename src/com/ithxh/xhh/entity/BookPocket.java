package com.ithxh.xhh.entity;

import java.io.Serializable;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
import com.ithxh.baseCore.annotation.DB.Table;

@SuppressWarnings("serial")
@Table("book_pocket")
public class BookPocket implements Serializable{

	@PrimaryKey
	private String pocketid;
	private String userid;
	private String sellid;
	private String addtime;
	private int booknum;
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
