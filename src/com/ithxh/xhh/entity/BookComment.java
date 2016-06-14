package com.ithxh.xhh.entity;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
import com.ithxh.baseCore.annotation.DB.Table;
// default package

/**
 * BookComment entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Table("book_comment")
public class BookComment implements java.io.Serializable {

	// Fields
	@PrimaryKey("COMMENTID")
	private String commentid;
	private String commentuserid;
	private String commentbookid;
	private String commentcontent;
	private String commenttime;

	// Constructors

	/** default constructor */
	public BookComment() {
	}

	// Property accessors

	public String getCommentid() {
		return this.commentid;
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