package com.ithxh.xhh.vo;

/**
 * @category 
 * @Author 何建辉
 * @date 2015年9月30日 下午4:57:19
 */
public class SettingTableAndField {

	private String tableName;//表名
	private String userIdFieldName;//用户id字段名
	private String userId;//用户id值,需要赋值
	
	private String[] fieldNames;//需要修改的字段名称
	private String[] fieldValue;//字段值 需要赋值
	
	public SettingTableAndField(String tableName, String userIdFieldName,
			String[] fieldNames) {
		super();
		this.tableName = tableName;
		this.userIdFieldName = userIdFieldName;
		this.fieldNames = fieldNames;
	}
	public SettingTableAndField() {
		super();
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getUserIdFieldName() {
		return userIdFieldName;
	}
	public void setUserIdFieldName(String userIdFieldName) {
		this.userIdFieldName = userIdFieldName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String[] getFieldNames() {
		return fieldNames;
	}
	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}
	public String[] getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String[] fieldValue) {
		this.fieldValue = fieldValue;
	}
}
