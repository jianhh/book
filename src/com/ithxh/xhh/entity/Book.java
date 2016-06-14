package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
// default package
import com.ithxh.baseCore.annotation.DB.Table;


/**
 * Book entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Table("book")
public class Book implements java.io.Serializable {

	// Fields
	@PrimaryKey("BOOKID")
	private String bookid;
	private String bookname;
	private String bookisbn;
	private Integer booknum;
	private Double bookoldprice;
	private String bookauthor;
	private String bookpic;
	private String bookaddtime;
	private String bookpublish;
	private String bookpublishtime;
	private Integer bookpagesize;
	private String bookintro;
	private String bookremark;
	private String booktypeid;
	private String bookorigin;
	private String bookpv;

	// Constructors

	/** default constructor */
	public Book() {
	}

	// Property accessors
	
	public String getBookid() {
		return this.bookid;
	}

	public String getBookpv() {
		return bookpv;
	}

	public void setBookpv(String bookpv) {
		this.bookpv = bookpv;
	}

	public String getBookorigin() {
		return bookorigin;
	}

	public void setBookorigin(String bookorigin) {
		this.bookorigin = bookorigin;
	}

	public String getBookpublishtime() {
		return bookpublishtime;
	}

	public void setBookpublishtime(String bookpublishtime) {
		this.bookpublishtime = bookpublishtime;
	}

	public String getBooktypeid() {
		return booktypeid;
	}

	public void setBooktypeid(String booktypeid) {
		this.booktypeid = booktypeid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookisbn() {
		return this.bookisbn;
	}

	public void setBookisbn(String bookisbn) {
		this.bookisbn = bookisbn;
	}

	public Integer getBooknum() {
		return this.booknum;
	}

	public void setBooknum(Integer booknum) {
		this.booknum = booknum;
	}

	public Double getBookoldprice() {
		return this.bookoldprice;
	}

	public void setBookoldprice(Double bookoldprice) {
		this.bookoldprice = bookoldprice;
	}

	public String getBookauthor() {
		return this.bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public String getBookpic() {
		return this.bookpic;
	}

	public void setBookpic(String bookpic) {
		this.bookpic = bookpic;
	}

	public String getBookaddtime() {
		return this.bookaddtime;
	}

	public void setBookaddtime(String bookaddtime) {
		this.bookaddtime = bookaddtime;
	}

	public String getBookpublish() {
		return this.bookpublish;
	}

	public void setBookpublish(String bookpublish) {
		this.bookpublish = bookpublish;
	}

	public Integer getBookpagesize() {
		return this.bookpagesize;
	}

	public void setBookpagesize(Integer bookpagesize) {
		this.bookpagesize = bookpagesize;
	}

	public String getBookintro() {
		return this.bookintro;
	}

	public void setBookintro(String bookintro) {
		this.bookintro = bookintro;
	}

	public String getBookremark() {
		return this.bookremark;
	}

	public void setBookremark(String bookremark) {
		this.bookremark = bookremark;
	}

}