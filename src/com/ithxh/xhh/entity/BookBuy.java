package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
import com.ithxh.baseCore.annotation.DB.Table;
// default package

/**
 * BookBuy entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Table("book_buy")
public class BookBuy implements java.io.Serializable {

	// Fields
	@PrimaryKey("BUYID")
	private String buyid;
	private String userid;
	private String sellid;
	private Integer buynumber;
	private Double buyprice;
	private Double buytotalamount;
	private String buytime;
	private String buystatu;
	private String buyremark;

	// Constructors

	/** default constructor */
	public BookBuy() {
	}

	// Property accessors

	public String getBuyid() {
		return this.buyid;
	}

	public String getBuyremark() {
		return buyremark;
	}

	public void setBuyremark(String buyremark) {
		this.buyremark = buyremark;
	}

	public void setBuyid(String buyid) {
		this.buyid = buyid;
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

	public Integer getBuynumber() {
		return this.buynumber;
	}

	public void setBuynumber(Integer buynumber) {
		this.buynumber = buynumber;
	}

	public Double getBuyprice() {
		return this.buyprice;
	}

	public void setBuyprice(Double buyprice) {
		this.buyprice = buyprice;
	}

	public Double getBuytotalamount() {
		return this.buytotalamount;
	}

	public void setBuytotalamount(Double buytotalamount) {
		this.buytotalamount = buytotalamount;
	}

	public String getBuytime() {
		return this.buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public String getBuystatu() {
		return this.buystatu;
	}

	public void setBuystatu(String buystatu) {
		this.buystatu = buystatu;
	}

}