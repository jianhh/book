package com.ithxh.xhh.model;

//import org.apache.commons.lang.StringUtils;

/**
 * Bean类 - 系统配置
 * 
 * @author five
 * @version v1.0
 */

public class SysConfig {

	/**
	 * @category 网站基础路径
	 */
	private String sysBasePath = "http://localhost:8080";
	private String qiniuUrl = "http://7xs7s6.com1.z0.glb.clouddn.com/";
	private boolean openQiniuUpload = false;
	
	/**
	 * @category 上传编辑器临时文件夹
	 */
	private String ckUploadPath = "/H/ckUpload/pic";
	private String ckUploadPathTemp = "/H/pictemp/ckUpload/pic/";
	
	
	public String getQiniuUrl() {
		return qiniuUrl;
	}
	public void setQiniuUrl(String qiniuUrl) {
		this.qiniuUrl = qiniuUrl;
	}
	public boolean isOpenQiniuUpload() {
		return openQiniuUpload;
	}
	public void setOpenQiniuUpload(boolean openQiniuUpload) {
		this.openQiniuUpload = openQiniuUpload;
	}
	public String getSysBasePath() {
		return sysBasePath;
	}
	public void setSysBasePath(String sysBasePath) {
		this.sysBasePath = sysBasePath;
	}
	public String getCkUploadPath() {
		return ckUploadPath;
	}
	public void setCkUploadPath(String ckUploadPath) {
		this.ckUploadPath = ckUploadPath;
	}
	public String getCkUploadPathTemp() {
		return ckUploadPathTemp;
	}
	public void setCkUploadPathTemp(String ckUploadPathTemp) {
		this.ckUploadPathTemp = ckUploadPathTemp;
	}
	
}