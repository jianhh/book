package com.ithxh.xhh.vo.formbean;

import java.util.List;

// default package


/**
 * Booktype entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BookTypeVo implements java.io.Serializable {

	// Fields

	private String booktypeid;
	private String booktypename;
	private String booktyperemark;
	private String booktypefatherid;
	private List<BookTypeVo> list;
	// Constructors

	/** default constructor */
	public BookTypeVo() {
	}

	/** minimal constructor */
	public BookTypeVo(String booktypeid, String booktypename) {
		this.booktypeid = booktypeid;
		this.booktypename = booktypename;
	}

	/** full constructor */
	public BookTypeVo(String booktypeid, String booktypename,
			String booktyperemark) {
		this.booktypeid = booktypeid;
		this.booktypename = booktypename;
		this.booktyperemark = booktyperemark;
	}

	// Property accessors

	public String getBooktypeid() {
		return this.booktypeid;
	}

	public List<BookTypeVo> getList() {
		return list;
	}

	public void setList(List<BookTypeVo> list) {
		this.list = list;
	}

	public String getBooktypefatherid() {
		return booktypefatherid;
	}

	public void setBooktypefatherid(String booktypefatherid) {
		this.booktypefatherid = booktypefatherid;
	}

	public void setBooktypeid(String booktypeid) {
		this.booktypeid = booktypeid;
	}

	public String getBooktypename() {
		return this.booktypename;
	}

	public void setBooktypename(String booktypename) {
		this.booktypename = booktypename;
	}

	public String getBooktyperemark() {
		return this.booktyperemark;
	}

	public void setBooktyperemark(String booktyperemark) {
		this.booktyperemark = booktyperemark;
	}

}