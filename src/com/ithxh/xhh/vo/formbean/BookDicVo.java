package com.ithxh.xhh.vo.formbean;

// default package


@SuppressWarnings("serial")
public class BookDicVo implements java.io.Serializable {

	// Fields
	private String dicid;
	private String dicname;
	private String dictype;
	private String dicremark;
	
	public String getDicid() {
		return dicid;
	}
	public void setDicid(String dicid) {
		this.dicid = dicid;
	}
	public String getDicname() {
		return dicname;
	}
	public void setDicname(String dicname) {
		this.dicname = dicname;
	}
	public String getDictype() {
		return dictype;
	}
	public void setDictype(String dictype) {
		this.dictype = dictype;
	}
	public String getDicremark() {
		return dicremark;
	}
	public void setDicremark(String dicremark) {
		this.dicremark = dicremark;
	}

}