package com.ithxh.xhh.vo.formbean;
// default package

/**
 * BookComment entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BookCommentVo implements java.io.Serializable {

	// Fields

	private String commentid;
	private String commentuserid;
	private String username;
	private String commentbookid;
	private String bookname;
	private String commentcontent;
	private String commenttime;
	private String userpic;

	// Constructors

	/** default constructor */
	public BookCommentVo() {
	}

	// Property accessors

	public String getCommentid() {
		return this.commentid;
	}

	public String getUserpic() {
		return userpic;
	}

	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public String getCommentcontent() {
		return this.commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	public String getCommenttime() {
		return this.commenttime;
	}

	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}

	public String getCommentuserid() {
		return commentuserid;
	}

	public void setCommentuserid(String commentuserid) {
		this.commentuserid = commentuserid;
	}

	public String getCommentbookid() {
		return commentbookid;
	}

	public void setCommentbookid(String commentbookid) {
		this.commentbookid = commentbookid;
	}
}