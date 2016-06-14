package com.ithxh.xhh.vo.formbean;
// default package

/**
 * BookArticle entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BookArticleVo implements java.io.Serializable {

	// Fields

	private String articleid;
	private String articleuserid;
	private String username;
	private String picpath;
	private String articletitle;
	private String articlecontent;
	private String articletime;
	private Integer articlepv;
	private String articlepic;
	
	private BookUserVo userVo;

	// Constructors

	/** default constructor */
	public BookArticleVo() {
	}

	// Property accessors

	public String getArticleid() {
		return this.articleid;
	}

	public BookUserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(BookUserVo userVo) {
		this.userVo = userVo;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
}