package com.ithxh.xhh.vo;

/**
 * SysDic entity. @author MyEclipse Persistence Tools
 */

/**
 * @category 导入模板VO
 * @author xsh
 *
 */
public class ExcelTemplateVo implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private boolean isRequired ;//必填
	private String subjectName;//名称
	private String comment;//备注
	private int width;//宽度
	
	public ExcelTemplateVo() { 
	}
	
	/**
	 * 
	 * @param isRequired 必填
	 * @param subjectName 名称
	 * @param comment 备注
	 * @param width 宽度
	 */
	public ExcelTemplateVo(boolean isRequired,String subjectName, String comment,int width) {

		this.isRequired = isRequired;
		this.subjectName = subjectName;
		this.comment = comment;
		this.width = width;
	}
 
	
	public boolean isRequired() {
	
		return isRequired;
	}

	
	public void setRequired(boolean isRequired) {
	
		this.isRequired = isRequired;
	}


	public String getSubjectName() {
	
		return subjectName;
	}
	
	public void setSubjectName(String subjectName) {
	
		this.subjectName = subjectName;
	}
	
	public String getComment() {
	
		return comment;
	}
	
	public void setComment(String comment) {
	
		this.comment = comment;
	}

	
	public int getWidth() {
	
		return width;
	}

	
	public void setWidth(int width) {
	
		this.width = width;
	}



}