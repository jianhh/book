package com.ithxh.xhh.vo.formbean;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class BookSellVo implements Serializable{

	private String sellid;
	
	@NotBlank(message="提交信息错误，请刷新页面重新操作")
	private String bookid;
	private String bookname;
	
	@Max(value=10,message="折扣不能超过10折")
	@Min(value=0,message="折扣不能低于0折")
	private int discount;
	
	private double price;
	@Min(value=1,message="出售数量不能低于1本")
	private String num;
	private String remark;
	private String userid;
	private String username;
	private String sellStatus;
	private String selltime;
	private int sellnum;
	
	private BookUserVo userVo;
	private BookVo bookVo;
	
	public String getSellStatus() {
		return sellStatus;
	}
	public void setSellStatus(String sellStatus) {
		this.sellStatus = sellStatus;
	}
	public BookUserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(BookUserVo userVo) {
		this.userVo = userVo;
	}
	public BookVo getBookVo() {
		return bookVo;
	}
	public void setBookVo(BookVo bookVo) {
		this.bookVo = bookVo;
	}
	public int getSellnum() {
		return sellnum;
	}
	public void setSellnum(int sellnum) {
		this.sellnum = sellnum;
	}
	public String getSelltime() {
		return selltime;
	}
	public void setSelltime(String selltime) {
		this.selltime = selltime;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSellid() {
		return sellid;
	}
	public void setSellid(String sellid) {
		this.sellid = sellid;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
