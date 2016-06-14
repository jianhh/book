package com.ithxh.baseCore.baseUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ithxh.baseCore.model.Modular;
import com.ithxh.baseCore.model.Pager;

public class SqlBuilder {

	/**
	 * @category 获取对应的表名
	 * @param o
	 * @return
	 */
	public static String getTableName(Object o) {

		Field[] f = o.getClass().getDeclaredFields();
		String tableName = "";
		for (Field field : f) {
			if (field.getName() == "Table_Name") {
				try {
					tableName = field.get(o).toString();
					break;
				} catch (Exception e) {
					e.printStackTrace();
					tableName = "";
					Log4jUtil.error("", e);
				}
			}
		}
		return tableName;
	}

	/**
	 * @category 获取主键
	 * @param o
	 * @return
	 */
	public static String getPrimaryKey(Object o) {

		Field[] f = o.getClass().getDeclaredFields();
		String primaryKey = "";
		for (Field field : f) {
			if (field.getName() == "Primary_Key") {
				try {
					primaryKey = field.get(o).toString();
				} catch (Exception e) {
					primaryKey = "";
					Log4jUtil.error("", e);
				}
			}
		}
		return primaryKey;
	}

	/**
	 * @category 生成insert语句
	 * @param o
	 * @return   sql表示生产的语句， values 表示值
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> BuildInsertSql(Object o) {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> insertMap = new HashMap<String, Object>();
		Field[] f = o.getClass().getDeclaredFields();
		for (Field field : f) {
			PropertyDescriptor pd;
			Object value;
			try {
				// 获取get方法

				if (field.getName().equals("serialVersionUID") || field.getName().equals("Table_Name") || field.getName().equals("Primary_Key")) {
					continue;
				}

				pd = new PropertyDescriptor(field.getName(), o.getClass());
				Method getMethod = pd.getReadMethod();

				String fieldName = field.getName(); // 获取字段名
				Object fieldType = field.getGenericType(); // 获取类型

				if (StringUtils.isBlank(fieldName)) {
					continue; // 字段名为空则跳过
				}

				value = getMethod.invoke(o); // 执行get方法返回一个获取属性的值
				if (value != null) {
					if (fieldType == Date.class) {
						value = DateUtils.getDateTimeToString((Date) value);
					}
					map.put(fieldName, value);
				}
			} catch (Exception e) {
				value = null;
				Log4jUtil.error("", e);
				continue;// 如果没有get方法就跳过
			}
		}
		StringBuilder sql = new StringBuilder();

		Object[] args = new Object[map.size()];

		if (map.size() > 0) {

			StringBuilder fieldSql = new StringBuilder(); // 字段
			StringBuilder valueSql = new StringBuilder(); // 值

			Iterator<?> it = map.entrySet().iterator();
			int count = 1;
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();

				fieldSql.append("`" + entry.getKey() + "`");
				// valueSql.append(entry.getValue());
				valueSql.append("?");

				if (count < map.size()) {
					fieldSql.append(",");
					valueSql.append(",");
				}
				args[count - 1] = entry.getValue();
				count++;
			}
			sql.append("insert into `");
			sql.append(getTableName(o));
			sql.append("` (");

			// 获取字段
			sql.append(fieldSql);

			sql.append(") values (");
			// 获取值
			sql.append(valueSql);

			sql.append(")");
		}

		insertMap.put("sql", sql.toString());

		insertMap.put("values", args);

		return insertMap;
	}

	/**
	 * @category 生成update语句
	 * @param o
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> BuildUpdateSql(Object o) {

		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, Object> insertMap = new HashMap<String, Object>();

		Field[] f = o.getClass().getDeclaredFields();
		Object pkValue = null;
		for (Field field : f) {
			PropertyDescriptor pd;
			Object value;
			try {
				// 获取get方法
				if (field.getName().equals("serialVersionUID") || field.getName().equals("Table_Name") || field.getName().equals("Primary_Key")) {
					continue;
				}
				pd = new PropertyDescriptor(field.getName(), o.getClass());
				String fieldName = field.getName(); // 获取字段名
				// Object fieldType = field.getGenericType(); // 获取类型

				if (StringUtils.isBlank(fieldName)) {
					continue; // 字段名为空则或者为主键跳过
				}

				Method getMethod = pd.getReadMethod();

				if (fieldName.equals(getPrimaryKey(o))) {
					pkValue = getMethod.invoke(o);
					continue; // 字段名为主键跳过
				}
				value = getMethod.invoke(o); // 执行get方法返回一个获取属性的值
				if (value != null) { // 值为NULL 的字段 不做修改
					map.put(fieldName, value);
				}
			} catch (Exception e) {
				Log4jUtil.error("", e);
				value = null;
				continue;// 如果没有get方法就跳过
			}
		}
		StringBuilder sql = new StringBuilder();

		Object[] args = new Object[map.size()];

		if (map.size() > 0 && pkValue != null) {
			StringBuilder SetSql = new StringBuilder(); // 值
			Iterator it = map.entrySet().iterator();
			int count = 1;
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				SetSql.append("`" + entry.getKey() + "`=?");
				if (count < map.size()) {
					SetSql.append(",");
				}
				args[count - 1] = entry.getValue();
				count++;
			}
			sql.append("update `");
			sql.append(getTableName(o));
			sql.append("` set ");
			sql.append(SetSql);
			sql.append(" where ");
			sql.append(getPrimaryKey(o));
			sql.append(" = ");
			sql.append("'" + pkValue + "'");
		}
		insertMap.put("sql", sql.toString());
		insertMap.put("values", args);
		return insertMap;
	}

	/**
	 * @category 生成select 后面的属性 语句
	 * @param o
	 * @return
	 */
	public static String BuildSelectPropSql(Object o) {

		Field[] f = o.getClass().getDeclaredFields();
		StringBuffer strResult = new StringBuffer();
		int i = 0;
		for (Field field : f) {
			i++;
			if (!field.getName().equals("Table_Name") && !field.getName().equals("Primary_Key") && !field.getName().equals("serialVersionUID")) {
				strResult.append(" " + field.getName() + " ");
				if (i < f.length) {
					strResult.append(",");
				}
			}
		}
		String result= strResult.toString();
		if(result.substring(result.length()-1,result.length() ).equals(","))
		{
			result = result.substring(0, result.length()-1);
		}
					
		return result;
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
				&& innerKeyOrValue != null && innerKeyOrValue.length>0) {

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
