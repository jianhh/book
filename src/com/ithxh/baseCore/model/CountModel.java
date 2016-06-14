package com.ithxh.baseCore.model;

import java.util.HashMap;
import java.util.List;

/**
 * @category   统计用SQL查询语句
 * @Copyright Copyright(c) 2013
 * @Company
 * @author  five
 * @version V1.0
 * @date 2015-10-16
 */
public class CountModel implements java.io.Serializable {


	private static final long serialVersionUID = 1227138886099104761L;

	public static final Integer MAX_PAGE_SIZE = 50;// 每次最大记录数限制
	private List<?> list ;   // 数据List T 是查询结果返回的对象 也是查询条件中的字段
	private Integer pageNumber = 1;// 当前页码
	private Integer pageSize = 10; // 每页记录数
	private Integer totalCount = 0;// 总记录数
	private Integer pageCount = 0; // 总页数
	private int isHasPre;          // 是否有上一页 1表示有，-1表示无
	private int isHasNext;         // 是否有下一页 1表示有，-1表示无
	
//	SELECT  eduOrgId,eduOrgName,
//	SUM(IF(eduOrgId<>"",1,0)) AS '总计',
//	SUM(IF(xxbxlxm='21',1,0)) AS '小学-教学点',
//	SUM(IF(xxbxlxm='21' AND szdmzsx='1',1,0)) AS '小学-独立设置少数民族学校',
//	SUM(IF(xxbxlxm='31' ,1,0)) AS '初中-普通初中',
//	SUM(IF(xxbxlxm='31' AND szdmzsx='1',1,0)) AS '初中-普通初中 独立设置少数民族学校',
//	SUM(IF(xxbxlxm='345',1,0)) AS '初中-十二年一贯制'
//	FROM `sys_zxxx_xx`
//	GROUP BY eduOrgId
//	LIMIT 0,1
	
	@SuppressWarnings("rawtypes")
	private HashMap selectFieldList;  //
	private List<CountWhereModel> countWhereModels;   
	private String tableName;
	private String orderBy; // 排序字段 ,字符串 例如  createdate desc
    private String groupBy; // 分组字段 ,例如：eduOrgId

	public CountModel() {
		super();
	}


	@SuppressWarnings("rawtypes")
	public HashMap getSelectFieldList() {
		return selectFieldList;
	}

	@SuppressWarnings("rawtypes")
	public void setSelectFieldList(HashMap selectFieldList) {
		this.selectFieldList = selectFieldList;
	}



	public List<CountWhereModel> getCountWhereModels() {
		return countWhereModels;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setCountWhereModels(List<CountWhereModel> countWhereModels) {
		this.countWhereModels = countWhereModels;
	}


	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public int getIsHasPre() {
		return isHasPre;
	}

	public void setIsHasPre(int isHasPre) {
		this.isHasPre = isHasPre;
	}

	public int getIsHasNext() {
		return isHasNext;
	}

	public void setIsHasNext(int isHasNext) {
		this.isHasNext = isHasNext;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
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



//	public Integer getShowNumber() {
//		return showNumber;
//	}


//	public void setShowNumber(Integer showNumber) {
//		
//		if (showNumber < 1) {
//			showNumber = 1;
//		} else if (showNumber > MAX_PAGE_SIZE) {
//			showNumber = MAX_PAGE_SIZE;
//		}
//
//		this.showNumber = showNumber;
//	}


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