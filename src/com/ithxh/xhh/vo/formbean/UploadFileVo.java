package com.ithxh.xhh.vo.formbean;

import java.io.InputStream;
// default package

/**
 * Book entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class UploadFileVo implements java.io.Serializable {

	// Fields
	private String uploadid;
	private String uploadoldfilename;
	private String uploadnewfilename;
	private String uploadfilepath;
	private double uploadfilesize;
	private String uploadfiletype;
	private String uploadfileext;
	private String uploadmimetype;
	private String uploadtime;
	private String uploadip;
	private String uploadtransize;
	private String uploadstatus;
	private InputStream inputStream;
	private byte[] bytes;
	private String uploaddest;
	
	public String getUploaddest() {
		return uploaddest;
	}
	public void setUploaddest(String uploaddest) {
		this.uploaddest = uploaddest;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getUploadstatus() {
		return uploadstatus;
	}
	public void setUploadstatus(String uploadstatus) {
		this.uploadstatus = uploadstatus;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getUploadtransize() {
		return uploadtransize;
	}
	public void setUploadtransize(String uploadtransize) {
		this.uploadtransize = uploadtransize;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getUploadip() {
		return uploadip;
	}
	public void setUploadip(String uploadip) {
		this.uploadip = uploadip;
	}
	public String getUploadmimetype() {
		return uploadmimetype;
	}
	public void setUploadmimetype(String uploadmimetype) {
		this.uploadmimetype = uploadmimetype;
	}
	public String getUploadid() {
		return uploadid;
	}
	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}
	public String getUploadoldfilename() {
		return uploadoldfilename;
	}
	public void setUploadoldfilename(String uploadoldfilename) {
		this.uploadoldfilename = uploadoldfilename;
	}
	public String getUploadnewfilename() {
		return uploadnewfilename;
	}
	public void setUploadnewfilename(String uploadnewfilename) {
		this.uploadnewfilename = uploadnewfilename;
	}
	public String getUploadfilepath() {
		return uploadfilepath;
	}
	public void setUploadfilepath(String uploadfilepath) {
		this.uploadfilepath = uploadfilepath;
	}
	public double getUploadfilesize() {
		return uploadfilesize;
	}
	public void setUploadfilesize(double uploadfilesize) {
		this.uploadfilesize = uploadfilesize;
	}
	public String getUploadfiletype() {
		return uploadfiletype;
	}
	public void setUploadfiletype(String uploadfiletype) {
		this.uploadfiletype = uploadfiletype;
	}
	public String getUploadfileext() {
		return uploadfileext;
	}
	public void setUploadfileext(String uploadfileext) {
		this.uploadfileext = uploadfileext;
	}
}