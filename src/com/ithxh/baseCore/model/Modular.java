package com.ithxh.baseCore.model;

import java.util.List;

import com.ithxh.baseCore.baseUtils.ArrayUtil;

/**
 * @category  通用的根据条件获取部分模块化的数据,自动组装SQL语句
 * @Copyright Copyright(c) 2012
 * @Company
 * @author  five
 * @version V1.0
 * @date 2012-11-19
 */
public class Modular implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1227138886099104761L;

	public static final Integer MAX_PAGE_SIZE = 50;// 每次最大记录数限制

	private Integer showNumber = 5;    //在界面上显示的数据数量
	//private Integer showLength = 10;   //在界面上显示的每条数据的长度
	                                     //如果超过了长度将被截取 并加...
	//private String showFieldName= "";  // 查询结果的对象中 要截取的内容的字段名称。
	
	private List<?> list ;   // 数据List T 是查询结果返回的对象 也是查询条件中的字段

	private String[] fieldNames;  // 查找属性名称 属性之间 用and 关联   例如 ：
	private String[] relations;  // 内部设定的关键字  属性与关键字的对应关系 例如 {" < "," > ", " = "," like "} 等 与 ppNames 对应  不需要另外手工输入
	private Object[] fieldValues;//  

	private String orderBy;// 排序字段 ,字符串 例如  createdate desc
	

	/**
	例如：
	pagerTmp.setInnerKeyOrPropertys(new String[] { "eduOrgName#like", "eduOrgPerson#like", "eduOrgPersonTel#like" });
	pagerTmp.setInnerKeyOrValue("7654321");

	pagerTmp.setPropertys(new String[]{"eduOrgFatherId#="});
	pagerTmp.setKeywords(new String[]{"123456"});
	*/
	
	public Modular() {
		super();
	}


	/**
	 * @category 获取当前分页的数据结果集
	 * @param ids
	 */
	public List<?> getList() {

		return list;
	}

	/**
	 * @category 设置当前分页的数据结果集
	 * @param ids
	 */
	public void setList(List<?> list) {

		this.list = list;
	}


	
	public String[] getFieldNames() {
	
		return fieldNames;
	}

	
	public void setFieldNames(String[] fieldNames) {
		fieldNames = ArrayUtil.getListReplace(fieldNames);
		List<String[]> list = ArrayUtil.getListArray(fieldNames, "#");
		if (null != list) {
			this.fieldNames = list.get(0);
			this.setRelations(list.get(1));
		}
	}

	

	public Integer getShowNumber() {
		return showNumber;
	}


	public void setShowNumber(Integer showNumber) {
		
		if (showNumber < 1) {
			showNumber = 1;
		} else if (showNumber > MAX_PAGE_SIZE) {
			showNumber = MAX_PAGE_SIZE;
		}

		this.showNumber = showNumber;
	}


//	public Integer getShowLength() {
//		return showLength;
//	}
//
//
//	public void setShowLength(Integer showLength) {
//		this.showLength = showLength;
//	}
//
//
//	public String getShowFieldName() {
//		return showFieldName;
//	}
//
//
//	public void setShowFieldName(String showFieldName) {
//		this.showFieldName = showFieldName;
//	}


	public Object[] getFieldValues() {

		return fieldValues;
	}

	// /**
	// * @category 获取查询条件中各个字段的值
	// * @return
	// */
	public void setFieldValues(Object[] fieldValues) {

		this.fieldValues = fieldValues;
	}

	/**
	 * @category 获取对应字段名称和字段值得关系
	 * @return
	 */
	public String[] getRelations() {

		return relations;
	}

	/**
	 * @category 设置对应字段名称和字段值的关系
	 * @param rss
	 */
	private void setRelations(String[] relations) {

		this.relations = relations;
	}

	/**
	 * @category 获取基础查询条件 SQL 语句的的排序语句
	 * @return
	 */
	public String getOrderBy() {

		return orderBy;
	}

	/**
	 * @category 设置基础查询条件 SQL语句的的排序语句
	 * @param orderBy
	 */
	public void setOrderBy(String orderBy) {

		this.orderBy = orderBy;
	}

	
}