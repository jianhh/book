package com.ithxh.xhh.vo.formbean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookCollectVo implements Serializable{

	private String collectid;
	private String userid;
	private String bookid;
	private String addtime;
	public String getCollectid() {
		return collectid;
	}
	public void setCollectid(String collectid) {
		this.collectid = collectid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
}
