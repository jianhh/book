package com.ithxh.xhh.vo.formbean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookAreaVo implements Serializable{

	private int code;
	private int parent_id;
	private String area;
	private String pinyin;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
}
