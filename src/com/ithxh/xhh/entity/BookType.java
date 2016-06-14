package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
import com.ithxh.baseCore.annotation.DB.Table;
// default package


/**
 * Booktype entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Table("book_type")
public class BookType implements java.io.Serializable {

	// Fields
	@PrimaryKey("BOOKTYPEID")
	private String booktypeid;
	private String booktypename;
	private String booktyperemark;
	private String booktypefatherid;

	// Constructors

	/** default constructor */
	public BookType() {
	}

	/** minimal constructor */
	public BookType(String booktypeid, String booktypename) {
		this.booktypeid = booktypeid;
		this.booktypename = booktypename;
	}

	/** full constructor */
	public BookType(String booktypeid, String booktypename,
			String booktyperemark) {
		this.booktypeid = booktypeid;
		this.booktypename = booktypename;
		this.booktyperemark = booktyperemark;
	}

	// Property accessors

	public String getBooktypeid() {
		return this.booktypeid;
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