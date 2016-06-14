package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
import com.ithxh.baseCore.annotation.DB.Table;
// default package

/**
 * BookArticle entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Table("book_article")
public class BookArticle implements java.io.Serializable {

	// Fields
	@PrimaryKey("ARTICLEID")
	private String articleid;
	private String articleuserid;
	private String articletitle;
	private String articlecontent;
	private String articletime;
	private Integer articlepv;
	private String articlepic;

	// Constructors

	/** default constructor */
	public BookArticle() {
	}

	// Property accessors

	public String getArticleid() {
		return this.articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	public String getArticletitle() {
		return this.articletitle;
	}

	public void setArticletitle(String articletitle) {
		this.articletitle = articletitle;
	}

	public String getArticlecontent() {
		return this.articlecontent;
	}

	public void setArticlecontent(String articlecontent) {
		this.articlecontent = articlecontent;
	}

	public String getArticletime() {
		return this.articletime;
	}

	public void setArticletime(String articletime) {
		this.articletime = articletime;
	}

	public Integer getArticlepv() {
		return this.articlepv;
	}

	public void setArticlepv(Integer articlepv) {
		this.articlepv = articlepv;
	}

	public String getArticlepic() {
		return this.articlepic;
	}

	public void setArticlepic(String articlepic) {
		this.articlepic = articlepic;
	}

	public String getArticleuserid() {
		return articleuserid;
	}

	public void setArticleuserid(String articleuserid) {
		this.articleuserid = articleuserid;
	}
}