package com.ithxh.xhh.model;

import java.util.List;

/**
 * SysDicAddr entity. @author MyEclipse Persistence Tools
 */


//每行的导入结果
public class SysImportResultModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8215375350820813946L;
	private String msg;
	private List<String> excelData;
	private Boolean result;
	private String code;
	
	public String getMsg() {
	
		return msg;
	}
	
	public void setMsg(String msg) {
	
		this.msg = msg;
	}
	
	@SuppressWarnings("rawtypes")
	public List getExcelData() {
	
		return excelData;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setExcelData(List excelData) {
	
		this.excelData = excelData;
	}
	
	public Boolean getResult() {
	
		return result;
	}
	
	public void setResult(Boolean result) {
	
		this.result = result;
	}

	
	public String getCode() {
	
		return code;
	}

	
	public void setCode(String code) {
	
		this.code = code;
	}
 

     

 

}