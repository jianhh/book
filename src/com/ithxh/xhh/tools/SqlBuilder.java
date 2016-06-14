package com.ithxh.xhh.tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.ithxh.baseCore.annotation.DB.PrimaryKey;
import com.ithxh.baseCore.annotation.DB.Table;
import com.ithxh.baseCore.baseUtils.StringUtil;

public class SqlBuilder {
	
	@SuppressWarnings("rawtypes")
	private Class clazz;

	@SuppressWarnings("rawtypes")
	public SqlBuilder(Class clazz){
		this.clazz = clazz;
	}

	public SqlBuilder() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public String getTableName(){
		Table table = (Table) clazz.getAnnotation(Table.class);
		return table.value();
	}
	
	/**
	 * @Description:  获取主键的名称
	 * @author: 何建辉
	 * @date 2016年2月7日 下午3:31:14
	 * @param @return
	 */
	public String getPrimaryKey(){
		Field[] fields = clazz.getDeclaredFields();
		for(Field f:fields){
			PrimaryKey key = f.getAnnotation(PrimaryKey.class);
			if(key!=null){
				if(StringUtil.isEmpty(key.value())){
					f.setAccessible(true);
					return f.getName();
				}
				return key.value().toUpperCase();
			}
		}
		return null;
	}
	
	/**
	 * @Description:  获取主键的值
	 * @author: 何建辉
	 * @date 2016年2月7日 下午3:31:01
	 * @param @return
	 */
	public Object getPrimaryKeyValue(Object obj){
		Field[] fields = clazz.getDeclaredFields();
		for(Field f:fields){
			PrimaryKey key = f.getAnnotation(PrimaryKey.class);
			if(key!=null){
				try {
					f.setAccessible(true);
					return f.get(obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
	
	//获取映射名称
	public String getMappingName2DB(Field field){
		//判断是否是主键
		PrimaryKey key = field.getAnnotation(PrimaryKey.class);
		field.setAccessible(true);
		if(key!=null){
			String value = key.value();
			if(StringUtil.isEmpty(value)){
				return field.getName().toUpperCase();
			}
			return value;
		}
		//判断是否是外键
		//判断是否是字段 暂时没有其他注解，默认没有，直接返回字段名称大写
		return field.getName().toUpperCase();
	}
	

	public String getInsertSql(){
		StringBuilder sb = new StringBuilder();
		sb.append("insert into ").append(getTableName()).append("(");
		Field[] fields = clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			String columnName = getMappingName2DB(fields[i]);
			if(i==fields.length-1){
				sb.append(columnName);
			}else {
				sb.append(columnName).append(",");
			}
		}
		sb.append(") values(");
		for(int i=0;i<fields.length;i++){
			if(i==fields.length-1){
				sb.append("?");
			}else {
				sb.append("?,");
			}
		}
		sb.append(");");
		return sb.toString();
	}
	
	
	public String getDeleteSql(){
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ").append(getTableName()).append(" where ").append(getPrimaryKey()).append(" = ?");
		return sb.toString();
	}
	
	
	public String getUpdateSql(Object obj){
		
		Field[] fields = obj.getClass().getDeclaredFields();
		if(fields==null){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("update ").append(getTableName()).append(" set ");
		for(int i=0;i<fields.length;i++){
			try {
				fields[i].setAccessible(true);
				Object object = fields[i].get(obj);
				if(object!=null){
					if(fields[i].getAnnotation(PrimaryKey.class)!=null){
						continue;
					}
					
					sb.append(fields[i].getName().toUpperCase()).append(" = ? , ");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String str = sb.toString().substring(0,sb.toString().length()-2);
		str += " where "+getPrimaryKey()+" = ?";
		return str;
	}
	
	
	public String getFindSql(){
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ").append(getTableName()).append(" where ").append(getPrimaryKey()).append(" = ?");
		return sb.toString();
	}
	
	public String getFindListSql(String[] ids){
		StringBuilder sb = new StringBuilder();
		sb.append("select * from ").append(getTableName()).append(" where ").append(getPrimaryKey()).append(" in (");
		for(int i=0;i<ids.length;i++){
			if(i==ids.length-1){
				sb.append(" ? ");
			}else {
				sb.append(" ? ,");
			}
		}
		sb.append(" )");
		return sb.toString();
	}
	
	/**
	 * @Description:  返回所有参数字段
	 * @author: 何建辉
	 * @date 2016年2月7日 下午3:37:44
	 * @param @param obj
	 * @param @return
	 */
	public Object[] getAllParameter(Object obj){
		List<Object> list = new ArrayList<Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		if(fields==null){
			return null;
		}
		for(int i=0;i<fields.length;i++){
			Object object;
			try {
				fields[i].setAccessible(true);
				object = fields[i].get(obj);
				list.add(object);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		Object[] objs = new Object[list.size()];
		for(int i =0;i<list.size();i++){
			objs[i]=list.get(i);
		}
		return objs;
	}
	
	/**
	 * @Description:  返回所有参数字段
	 * @author: 何建辉
	 * @date 2016年2月7日 下午3:37:44
	 * @param @param obj
	 * @param @return
	 */
	public Object[] getUpdateAllParameter(Object obj){
		List<Object> list = new ArrayList<Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		if(fields==null){
			return null;
		}
		Object primaryKey = getPrimaryKeyValue(obj);
		for(int i=0;i<fields.length;i++){
			Object object;
			try {
				fields[i].setAccessible(true);
				object = fields[i].get(obj);
				list.add(object);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		if(primaryKey!=null){
			list.add(primaryKey);
		}
		Object[] objs = new Object[list.size()];
		for(int i =0;i<list.size();i++){
			objs[i]=list.get(i);
		}
		return objs;
	}
				
	/**
	 * @Description:  获取所有非空参数字段名称，主键放到后面
	 * @author: 何建辉
	 * @date 2016年2月7日 下午3:35:29
	 * @param @param obj
	 * @param @return
	 */
	public Object[] getNotNullAllParameter(Object obj){
		List<Object> list = new ArrayList<Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		if(fields==null){
			return null;
		}
		Object primaryKey = getPrimaryKeyValue(obj);
		for(int i=0;i<fields.length;i++){
			Object object;
			try {
				fields[i].setAccessible(true);
				object = fields[i].get(obj);
				if(fields[i].getAnnotation(PrimaryKey.class)!=null){
					continue;
				}else{
					if(object!=null){
						list.add(object);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list.add(primaryKey);
		Object[] objs = new Object[list.size()];
		for(int i =0;i<list.size();i++){
			objs[i]=list.get(i);
		}
		return objs;
	}
}
