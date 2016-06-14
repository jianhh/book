package com.ithxh.baseCore.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ithxh.baseCore.baseUtils.ArrayUtil;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.xhh.constant.StaticConst.AndOrOr;

/**
 * @category 通用的分页类,自动组装SQL语句
 * @Copyright Copyright(c) 2012
 * @Company
 * @author 
 * @version V1.0
 * @date 2012-11-19
 */
public class Pager implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1227138886099104761L;

	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	private Integer pageNumber = 1;// 当前页码
	private Integer pageSize = 10;// 每页记录数
	private Integer totalCount = 0;// 总记录数
	private Integer pageCount = 0;// 总页数
	private List<?> list;// 数据List T 是查询结果返回的对象 也是查询条件中的字段
	private int isHasPre; // 是否有上一页 1表示有，-1表示无
	private int isHasNext; // 是否有下一页 1表示有，-1表示无


	private String[] ppNames; // 查找属性名称 属性之间 用and 关联   例如 ：
	private String[] rss; // 内部设定的关键字  属性与关键字的对应关系 例如 {" < "," > ", " = "," like "} 等 与 propertys 对应  不需要另外手工输入
	private String[] keywords;// 查找关键字

	private String[] innerPPAndRss; // 内部设定的关键字 需要查找的字段名称 之间 用or 关联,不需要另外手工输入
	private String[] innerKeyOrRss; // 属性与关键字的对应关系 例如 {" < "," > ", " = "," like "} 等 与 innerKeyOrPropertys 对应
	private String[] innerKeyOrValue; // 用来 or的关键字 手工输入的综合查询

	private String orderBy;// 排序字段 ,字符串 例如 createdate desc
	private String orderType; // 排序类型

	/**
	例如：
	pagerTmp.setInnerPPAndRss(new String[] { "eduOrgName#like", "eduOrgPerson#like", "eduOrgPersonTel#like" });
	pagerTmp.setInnerKeyOrValue("7654321");
		
	pagerTmp.setPropertys(new String[]{"eduOrgFatherId#="});
	pagerTmp.setKeywords(new String[]{"123456"});
	
	*/
	
	/**
	 * @category 添加条件
	 * @Description type为AndOrOr.AND AndOrOr.OR 
	 * @author 何建辉
	 * @date 2015年11月10日  下午3:06:20
	 * @param fieldName
	 * @param condiction
	 * @param value
	 * @param type
	 */
	public void addCondition(String fieldName,String condiction,String value,AndOrOr type){
		if(StringUtil.isEmpty(fieldName) || StringUtil.isEmpty(condiction) || StringUtil.isEmpty(type.getCode())){
			return;
		}
		if(type.getCode().equals(AndOrOr.AND.getCode())){
			List<String> list;
			if(ppNames!=null && ppNames.length>0){
				list = new ArrayList<String>(Arrays.asList(ppNames));
			}else {
				list = new ArrayList<String>();
			}
			
			list.add(fieldName);
			ppNames = ListUtil.toArray(list);
			
			if(rss!=null && rss.length>0){
				list = new ArrayList<String>(Arrays.asList(rss));
			}else {
				list = new ArrayList<String>();
			}
			list.add(condiction);
			rss = ListUtil.toArray(list);
			
			if(keywords!=null && keywords.length>0){
				list = new ArrayList<String>(Arrays.asList(keywords));
			}else {
				list = new ArrayList<String>();
			}
			list.add(value);
			keywords = ListUtil.toArray(list);
			
		}else {
			List<String> list;
			if(innerPPAndRss!=null && innerPPAndRss.length>0){
				list = new ArrayList<String>(Arrays.asList(innerPPAndRss));
			}else {
				list = new ArrayList<String>();
			}
			list.add(fieldName);
			innerPPAndRss = ListUtil.toArray(list);
			
			if(innerKeyOrRss!=null && innerKeyOrRss.length>0){
				list = new ArrayList<String>(Arrays.asList(innerKeyOrRss));
			}else {
				list = new ArrayList<String>();
			}
			list.add(condiction);
			innerKeyOrRss = ListUtil.toArray(list);
			
			
			if(innerKeyOrValue!=null && innerKeyOrValue.length>0){
				list = new ArrayList<String>(Arrays.asList(innerKeyOrValue));
			}else {
				list = new ArrayList<String>();
			}
			list.add(value);
			innerKeyOrValue = ListUtil.toArray(list);
		}
	}
	
	public Pager() {

		super();
		// ValidatorPager();
	}

	public Pager(Pager pager) {

		super();
		this.pageCount = pager.getPageCount();
		this.pageSize = pager.getPageSize();
		ValidatorPager();
	}

	public Pager(int pagerNo, int pagerSize) {

		super();
		this.pageNumber = pagerNo;
		this.pageSize = pagerSize;
	}

	/**
	 * @category 获取当前页码
	 * @param ids
	 */
	public Integer getPageNumber() {

		return pageNumber;
	}

	/**
	 * @category 验证pager 数据是否符合规范
	 * @return
	 */
	private void ValidatorPager() {

		//
		if (null != ppNames || null != keywords) // 只要有一个不为空，就做这样的检查
		{
			if (ppNames.length != keywords.length) {
				System.out.println("开发注意：pager 里面的条件参数组不配对，将会产生异常");
			}
		}
	}

	/**
	 * @category 设置当前页码
	 * @param ids
	 */
	public void setPageNumber(Integer pageNumber) {

		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	/**
	 * @category 获取每页大小
	 * @param ids
	 */
	public Integer getPageSize() {

		return pageSize;
	}

	/**
	 * @category 设置每页大小
	 * @param ids
	 */
	public void setPageSize(Integer pageSize) {

		if (pageSize < 1) {
			pageSize = 1;
		} else if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	/**
	 * @category 获取总数据量
	 * @param ids
	 */
	public Integer getTotalCount() {

		return totalCount;
	}

	/**
	 * @category 设置总数据量
	 * @param ids
	 */
	public void setTotalCount(Integer totalCount) {
		
		this.totalCount = totalCount;
	}

	/**
	 * @category 获取总页码
	 * @param ids
	 */
	public Integer getPageCount() {

		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount++;
		}
		return pageCount;
	}

	/**
	 * @category 是否有下一页
	 * @param ids
	 */
	private boolean getHasNext() {

		return (pageNumber + 1 <= getPageCount());
	}

	/**
	 * @category 当有下一页时获取下一页页码
	 * @param ids
	 */
	public int getNextPage() {

		if (getHasNext())
			return pageNumber + 1;
		else
			return pageNumber;
	}

	/**
	 * @category 获取是否有上一页
	 * @param ids
	 */
	private boolean getHasPre() {

		return (pageNumber - 1 >= 1);
	}

	/**
	 * @category 获取是否有上一页
	 * @param ids
	 */
	public int getIsHasPre() {

		if (getHasPre())
			isHasPre = 1;
		else
			isHasPre = -1;
		return isHasPre;
	}

	/**
	 * @category 设置是否有上一页
	 * @param ids
	 */
	public void setIsHasPre(int isHasPre) {

		this.isHasPre = isHasPre;
	}

	/**
	 * @category 获取是否有下一页
	 * @param ids
	 */
	public int getIsHasNext() {

		if (getHasNext())
			isHasNext = 1;
		else
			isHasNext = -1;
		return isHasNext;
	}

	/**
	 * @category 设置是否有下一页
	 * @param ids
	 */
	public void setIsHasNext(int isHasNext) {

		this.isHasNext = isHasNext;
	}

	/**
	 * @category 取得上页的页号,序号从1开始.
	 * @param ids
	 */
	public int getPrePage() {

		if (getHasPre())
			return pageNumber - 1;
		else
			return pageNumber;
	}

	/**
	 * @category 设置总页数
	 * @param ids
	 */
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

	/**
	 * @category 根据pageNumber和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
	 * @param ids
	 */
	public int getFirst() {

		int  r = ((pageNumber - 1) * pageSize);
		return r;
	}

	
	public String[] getPpNames() {
	
		return ppNames;
	}

	
	public void setPpNames(String[] ppNames) {
		ppNames = ArrayUtil.getListReplace(ppNames);
		List<String[]> list = ArrayUtil.getListArray(ppNames, "#");
		if (null != list) {
			this.ppNames = list.get(0);
			this.setRss(list.get(1));
		}
	}

	

	//
	// /**
	// * @category 设置查询条件中的各个字段的值
	// * @param keywords
	// */

	public String[] getKeywords() {

		return keywords;
	}

	// /**
	// * @category 获取查询条件中各个字段的值
	// * @return
	// */
	public void setKeywords(String[] keywords) {

		this.keywords = keywords;
	}

	/**
	 * @category 获取对应字段名称和字段值得关系
	 * @return
	 */
	public String[] getRss() {

		return rss;
	}

	/**
	 * @category 设置对应字段名称和字段值的关系
	 * @param rss
	 */
	private void setRss(String[] rss) {

		this.rss = rss;
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

	/**
	 * @category 排序类型
	 * @return
	 */
	public String getOrderType() {

		return orderType;
	}

	/**
	 * @category 排序类型
	 * @param orderType
	 */
	public void setOrderType(String orderType) {

		this.orderType = orderType;
	}

	public String[] getInnerPPAndRss() {

		return innerPPAndRss;
	}

	public void setInnerPPAndRss(String[] innerPPAndRss) {
		innerPPAndRss = ArrayUtil.getListReplace(innerPPAndRss);
		List<String[]> list = ArrayUtil.getListArray(innerPPAndRss, "#");
		if (null != list) {
			this.innerPPAndRss = list.get(0);
			this.setInnerKeyOrRss(list.get(1));
		}
		// ValidatorPager();
		// this.innerKeyOrPropertys = innerKeyOrPropertys;
		if(innerKeyOrValue!=null && innerKeyOrValue.length>0){
			if(this.innerKeyOrValue.length==1){
				List<String> list1 = new ArrayList<String>();
				for (int i = 0; i < innerPPAndRss.length; i++) {
					list1.add(innerKeyOrValue[0]);
				}
				this.innerKeyOrValue = ListUtil.toArray(list1);
			}
		}else {
			List<String> list1 = new ArrayList<String>();
			if(innerPPAndRss!=null && innerPPAndRss.length>0){
				for (int i = 0; i < innerPPAndRss.length; i++) {
					list1.add("");
				}
				this.innerKeyOrValue = ListUtil.toArray(list1);
			}
		}
	}

	public String[] getInnerKeyOrRss() {

		return innerKeyOrRss;
	}

	private void setInnerKeyOrRss(String[] innerKeyOrRss) {

		this.innerKeyOrRss = innerKeyOrRss;
	}

	public String[] getInnerKeyOrValue() {
		return innerKeyOrValue;
	}

	public void setInnerKeyOrValue(String[] innerKeyOrValue) {
		//如果关键字只有一个，那么复制跟条件相同个数（<>=like）的关键字形成新的数据
//		if(innerKeyOrValue!=null && innerKeyOrValue.length>0){
//			
//			if(innerKeyOrValue.length==1){
//				List<String> list = new ArrayList<String>();
//				for (int i = 0; i < innerPPAndRss.length; i++) {
//					list.add(innerKeyOrValue[0]);
//				}
//				this.innerKeyOrValue = ListUtil.toArray(list);
//			}
//		}else{
			this.innerKeyOrValue = innerKeyOrValue;
//		}
		
	}
}