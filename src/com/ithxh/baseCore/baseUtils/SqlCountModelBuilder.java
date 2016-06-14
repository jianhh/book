package com.ithxh.baseCore.baseUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ithxh.baseCore.model.CountModel;
import com.ithxh.baseCore.model.CountWhereModel;
import com.ithxh.baseCore.model.Modular;
import com.ithxh.baseCore.model.Pager;

public class SqlCountModelBuilder {

	
	/**
	 * @category 生成select 后面的属性 语句
	 * @param o
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String buildSelectSql(CountModel countModel) {

		String resultStr = "";
		HashMap hm = countModel.getSelectFieldList();
		if(hm!=null)
		{
			Iterator iter = hm.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				resultStr = resultStr + key+" as '"+val+"', ";
			}
		}
		
		resultStr = resultStr + buildSelectSumSql(countModel);
		
		if(!resultStr.equals(""))
		{
			resultStr = resultStr.substring(0, resultStr.length()-1); //去掉 最后的 ,  
		}
		
		return resultStr;
	}
	
	
	//	SELECT  eduOrgId AS "组织机构ID" ,eduOrgName AS "组织机构名称",
	//	SUM(IF(eduOrgId<>"",1,0)) AS '总计',
	//	SUM(IF(xxbxlxm='21',1,0)) AS '小学-教学点',
	//	SUM(IF(xxbxlxm='21' AND szdmzsx='1',1,0)) AS '小学-独立设置少数民族学校',
	//	SUM(IF(xxbxlxm='31' ,1,0)) AS '初中-普通初中',
	//	SUM(IF(xxbxlxm='31' AND szdmzsx='1',1,0)) AS '初中-普通初中 独立设置少数民族学校',
	//	SUM(IF(xxbxlxm='345',1,0)) AS '初中-十二年一贯制'
	//	FROM `sys_zxxx_xx`
	//	GROUP BY eduOrgId
	
	/**
	 * @category 生成统计select 语句 ,尾部 可能包含 有逗号
	 * @param countModel
	 * @return
	 */
	private static String buildSelectSumSql(CountModel countModel)
	{
		String resultStr = "";
		List<CountWhereModel> countWhereModels = countModel.getCountWhereModels();
		if(countWhereModels!=null&&countWhereModels.size()>0)
		{
			for(int i=0;i<countWhereModels.size();i++)
			{
				CountWhereModel countWhereModel = countWhereModels.get(i);
				String[] fileNames = countWhereModel.getFileName();
				String[] relations =  countWhereModel.getRelation();
				String[] fileValues = countWhereModel.getFileValue();
				resultStr = resultStr+" sum(if(";
				String smallStr = ""; //存放每一个条件里面的 条件
				
				for(int j=0;j<fileNames.length;j++)
				{
					smallStr = smallStr +fileNames[j]+relations[j]+"'"+fileValues[j]+"' and ";
				}
				if(!smallStr.equals(""))
				{
					smallStr = smallStr.substring(0, smallStr.length()-4);  //去掉and
					resultStr = resultStr+smallStr;
				}
				
				resultStr = resultStr+",1,0)) as '"+countWhereModel.getAsChineseName()+"',";
				 // ,1,0)) as '"++"'"
			}
			
		
		}
		return resultStr;
	}

	/**
	 * @category 生成查询 后面的where 语句 包含 order by 和limit
	 * @author five 2014-5-14
	 * @param o
	 * @return
	 */
	public static String BuildSelectWhereSql(Pager pager, String andOrOr) {

		String[] ppNames = pager.getPpNames();
		String[] rss = pager.getRss();
		String[] keywords = pager.getKeywords();

		String orderBy = pager.getOrderBy();
		StringBuffer strResult = new StringBuffer();
		int haveData = 0;// 监测有property数据时记录
		int noData = 0;// 监测无property数据时记录
		if (null != ppNames) {
			for (int i = 0; i < ppNames.length; i++) {
				if (null != keywords &&keywords.length>i&& null != keywords[i] && !("").equals(keywords[i].trim())) {
					haveData++;// 有数据时记录

					strResult.append(" " + ppNames[i] + " " + rss[i] + " ? ");
					if (i < ppNames.length - 1 && null != keywords[i + 1] && !("").equals(keywords[i + 1].trim())) {
						strResult.append(andOrOr);
					}

				} else {// 无数据时记录
					noData++;
					if (noData > 0 && haveData > 0 && (i+1)<keywords.length) {
						if (i < ppNames.length - 1 && null != keywords && null != keywords[i + 1] && !("").equals(keywords[i + 1].trim())) {// 不为最后一条时不加上and、or

							strResult.append(andOrOr);
						}

					}
				}
			}

			if (strResult.toString().trim().endsWith(andOrOr.trim())) {

				strResult = new StringBuffer(strResult.toString().trim().substring(0, strResult.toString().trim().lastIndexOf(andOrOr.trim())));
			}

		}
		String orString = getManyFeildOrForKey(pager);
		if (null != orString) {
			if (null != strResult.toString() && !strResult.toString().trim().equals("")) {
				strResult.append(" and (" + orString + " ) ");
			} else {
				strResult.append("  " + orString + "  ");

			}
		}
		if (null != orderBy && !orderBy.equals("")) {

			strResult.append(" order by " + orderBy);
		}
		strResult.append(" limit " + pager.getFirst() + " , " + pager.getPageSize());
		return strResult.toString();
	}

	/**
	 * @category 生成查询 后面的where 语句 不包含 order by 和limit 用来计算总页数的
	 * @author five 2014-5-14
	 * @param o
	 * @return
	 */
	public static String BuildCountWhereSql(Pager pager, String andOrOr) {

		String[] ppNames = pager.getPpNames();
		String[] rss = pager.getRss();
		String[] keywords = pager.getKeywords();

		int haveData = 0;// 监测有property数据时记录
		int noData = 0;// 监测无property数据时记录
		StringBuffer strResult = new StringBuffer();
		if (null != ppNames) {
			//先 拼装keywords ，后面再拼装 like 关键字的情况
			for (int i = 0; i < ppNames.length; i++) {
				if (null != keywords &&keywords.length>i&& null != keywords[i] && !("").equals(keywords[i].trim())) {
					haveData++;// 有数据时记录
					strResult.append(" " + ppNames[i] + " " + rss[i] + " ? ");
					if (i < ppNames.length - 1 && null != keywords[i + 1] && !("").equals(keywords[i + 1].trim())) {
						strResult.append(andOrOr);
					}
				} else {// 无数据时记录
					noData++;

					if (noData > 0 && haveData > 0 && (i+1)<keywords.length) {
						if (i < ppNames.length - 1 && null != keywords && null != keywords[i + 1] && !("").equals(keywords[i + 1].trim())) {// 不为最后一条时不加上and、or

							strResult.append(andOrOr);
						}

					}

				}
			}
		}
		// 修正SQL

		if (strResult.toString().trim().endsWith(andOrOr.trim())) {

			strResult = new StringBuffer(strResult.toString().trim().substring(0, strResult.toString().trim().lastIndexOf(andOrOr.trim())));
		}
        //再拼装一个关键字对 多个字段的情况
		String orString = getManyFeildOrForKey(pager);
		if (null != orString) {
			if (null != strResult.toString() && !strResult.toString().trim().equals("")) {
				strResult.append(" and (" + orString + " ) ");
			} else {
				strResult.append("  " + orString + "  ");
			}
		}
		return strResult.toString();
	}

	/**
	 * @category 按照顺序拼装关键字
	 * @param pager
	 * @return
	 */
	public static Object[] BuildKeyWord(Pager pager) {

	
		String[] innerKeyOrRss = pager.getInnerKeyOrRss();    //关键字的 关系
        String[] innerKeyOrValue = pager.getInnerKeyOrValue();  //模糊关键字
//		String[] innerPPAndRss = pager.getInnerPPAndRss();
		
        String[] ppNames = pager.getPpNames();   //普通查询的字段名
    	String[] rss = pager.getRss();     //普通查询的关系
		String[] keywords = pager.getKeywords();       ////普通查询的关键字

		ArrayList<Object> keywordresult = new ArrayList<Object>();
		if (null != ppNames) {
			for (int i = 0; i < ppNames.length; i++) {
				if (null != keywords && keywords.length > i
						&& null != keywords[i]
						&& !("").equals(keywords[i].trim())) {
					if (rss[i].trim().equals("like")) {
						keywordresult.add("%" + keywords[i].trim() + "%");
					}else{
						keywordresult.add( keywords[i].trim());
					}
				
			}
		}
		}
		// 1、检查关键字属性是否为空
		if (innerKeyOrRss != null && innerKeyOrRss.length > 0
				&& innerKeyOrValue != null && !innerKeyOrValue.equals("")) {

			for (int i = 0; i < innerKeyOrRss.length; i++) {
				
				if (innerKeyOrRss[i].trim().equals("like")) {
					
					keywordresult.add( "%" +innerKeyOrValue[i].trim()+ "%");
				}else{
					keywordresult.add(  innerKeyOrValue[i].trim() );
				}
			}
		}
		return keywordresult.toArray();
	}

	/**
	 * 根据当前SQL，拼装出查询总数的SQL
	 * 
	 * @param sql
	 *            当前SQL语句
	 * @return
	 */
	public static String getCountSqlBySql(String sql) {

		String countSql = "SELECT COUNT(1)  ";

		String upperSql = sql.toUpperCase();
		int fromIndex = upperSql.indexOf("FROM");
		int whereIndex = upperSql.indexOf("WHERE");

		if (whereIndex > -1) {
			countSql = countSql + sql.substring(fromIndex, whereIndex);
		} else {
			countSql = countSql + sql.substring(fromIndex);
		}
		return countSql;
	}

	/**
	 * @category 对与输入一个关键字，对多个字段需要进行 or 查询的情况
	 */
	private static String getManyFeildOrForKey(Pager pager) {

		StringBuffer strResult = new StringBuffer();
		String[] innerPPAndRss = pager.getInnerPPAndRss();
		String[] innerKeyOrRss = pager.getInnerKeyOrRss();
		// 判断是否有需要 拼装or 字符串的需要。
		// 1、检查关键字属性是否为空
		if (innerPPAndRss == null || innerPPAndRss.length < 1) {
			return null;
		}
		// 如果
		if (pager.getInnerKeyOrValue() == null || pager.getInnerKeyOrValue().toString().equals("")) {
			return null;
		}

		for (int i = 0; i < innerPPAndRss.length; i++) {
			strResult.append(" " + innerPPAndRss[i] + " " + innerKeyOrRss[i] + " ? ");
			if (i < innerPPAndRss.length - 1) {
				strResult.append(" or ");
			}
		}
		return strResult.toString();
	}

	/**
	 * @category 生成查询 后面的 where 语句 包含 order by 和 limit
	 * @author five 2014-5-14
	 * @param modular
	 *            查询组建
	 * @param andOrOr
	 *            组建之间的对应关系
	 * @return
	 */
	public static String BuildSelectWhereSql(Modular modular, String andOrOr) {

		String[] fieldNames = modular.getFieldNames();
		String[] relations = modular.getRelations();
		Object[] fieldValues = modular.getFieldValues();

		String orderBy = modular.getOrderBy();
		StringBuffer strResult = new StringBuffer();
		int haveData = 0; // 监测有fieldNames数据时记录
		int noData = 0; // 监测无fieldNames数据时记录
		if (null != fieldNames) {
			for (int i = 0; i < fieldNames.length; i++) {
				if (null != fieldValues && fieldValues.length > i
						&& null != fieldValues[i]
						&& !("").equals(fieldValues[i].toString().trim())) {
					haveData++;// 有数据时记录

					strResult.append(" " + fieldNames[i] + " " + relations[i]
							+ " ? ");
					if (i < fieldNames.length - 1 && null != fieldNames[i + 1]
							&& !("").equals(fieldNames[i + 1].trim())) {
						strResult.append(andOrOr);
					}

				} else {// 无数据时记录
					noData++;
					if (noData > 0 && haveData > 0) {
						if (i < fieldNames.length - 1 && null != fieldValues
								&& null != fieldValues[i + 1]
								&& !("").equals(fieldValues[i + 1].toString().trim())) {

							strResult.append(andOrOr);
						}

					}
				}
			}

			if (strResult.toString().trim().endsWith(andOrOr.trim())) {

				strResult = new StringBuffer(strResult.toString().trim().substring(0,strResult.toString().trim().lastIndexOf(andOrOr.trim())));
			}
		}
		if (orderBy != null && !orderBy.equals("")) {
			strResult.append(" order by " + orderBy);
		}
		strResult.append(" limit 0 , " + modular.getShowNumber());
		return strResult.toString();
	}

	/**
	 * @category 按照顺序拼装关键字
	 * @param pager
	 * @return
	 */
	public static Object[] BuildKeyWord(Modular modular) {

		String[] fieldNames = modular.getFieldNames();
		String[] relations = modular.getRelations();
		Object[] fieldValues = modular.getFieldValues();
		
		ArrayList<Object> keywordresult = new ArrayList<Object>();
		if (null != fieldNames) {
			for (int i = 0; i < fieldNames.length; i++) {
				if (null != fieldValues && fieldValues.length > i
						&& null != fieldValues[i]
						&& !("").equals(fieldValues[i].toString().trim())) {
					if (relations[i].trim().equals("like")) {
						keywordresult.add("%" + fieldValues[i].toString().trim() + "%");
					}else{
						keywordresult.add(fieldValues[i]);
					}
				
			}
		}
		}
	
		return keywordresult.toArray();
	}

	
	public static void main(String[] args) {

	}
}
