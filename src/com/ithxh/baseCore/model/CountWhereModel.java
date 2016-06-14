package com.ithxh.baseCore.model;


/**
 * @category   统计用SQL查询语句---每一个字段条件的 
 * @Copyright Copyright(c) 2013
 * @Company
 * @author  five
 * @version V1.0
 * @date 2015-10-16
 */
public class CountWhereModel implements java.io.Serializable {


	private static final long serialVersionUID = 1227138886099104761L;

	private String[] fileName ;  //字段名  例如：
	private String[] fileValue ; //字段值
	private String[] relation ;  //字段名与字段值之间的关系
	//private String asKey;     //统计后生成的字段名字。
	private String asChineseName;  //统计后生成的中文名字。
	public String[] getFileName() {
		return fileName;
	}
	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}
	public String[] getFileValue() {
		return fileValue;
	}
	public void setFileValue(String[] fileValue) {
		this.fileValue = fileValue;
	}
	public String[] getRelation() {
		return relation;
	}
	public void setRelation(String[] relation) {
		this.relation = relation;
	}
//	public String getAsKey() {
//		return asKey;
//	}
//	public void setAsKey(String asKey) {
//		this.asKey = asKey;
//	}
	public String getAsChineseName() {
		return asChineseName;
	}
	public void setAsChineseName(String asChineseName) {
		this.asChineseName = asChineseName;
	}
	
	

	
}