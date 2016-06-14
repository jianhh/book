package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
import com.ithxh.baseCore.annotation.DB.Table;

@Table("book_need")
public class BookNeed {
	
	@PrimaryKey
	private String needid;
	private String bookid;
	private String userid;
	private String needtime;
	private String num;
	private String discount;
	private String remark;
	public String getNeedid() {
		return needid;
	}
	public void setNeedid(String needid) {
		this.needid = needid;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNeedtime() {
		return needtime;
	}
	public void setNeedtime(String needtime) {
		this.needtime = needtime;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
