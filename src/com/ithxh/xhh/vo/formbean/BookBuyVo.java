package com.ithxh.xhh.vo.formbean;
// default package

/**
 * BookBuy entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BookBuyVo implements java.io.Serializable {

	// Fields

	private String buyid;
	private String userid;
	private String username;
	private String sellid;
	private Integer buynumber;
	private Double buyprice;
	private Double buytotalamount;
	private String buytime;
	private String buystatu;
	private String bookname;
	private String bookpic;
	private String bookaddressid;
	private String buyremark;
	
	private BookVo bookVo;
	private BookUserVo bookUserVo;
	private BookSellVo bookSellVo;
	private BookAddressVo bookAddressVo;

	// Constructors

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

	public BookVo getBookVo() {
		return bookVo;
	}

	public void setBookVo(BookVo bookVo) {
		this.bookVo = bookVo;
	}

	public String getBookaddressid() {
		return bookaddressid;
	}

	public void setBookaddressid(String bookaddressid) {
		this.bookaddressid = bookaddressid;
	}

	public BookUserVo getBookUserVo() {
		return bookUserVo;
	}

	public void setBookUserVo(BookUserVo bookUserVo) {
		this.bookUserVo = bookUserVo;
	}

	public BookSellVo getBookSellVo() {
		return bookSellVo;
	}

	public void setBookSellVo(BookSellVo bookSellVo) {
		this.bookSellVo = bookSellVo;
	}

	public BookAddressVo getBookAddressVo() {
		return bookAddressVo;
	}

	public void setBookAddressVo(BookAddressVo bookAddressVo) {
		this.bookAddressVo = bookAddressVo;
	}

	public String getBookpic() {
		return bookpic;
	}

	public void setBookpic(String bookpic) {
		this.bookpic = bookpic;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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