package com.ithxh.baseCore.baseInterface;

import java.sql.SQLException;
import java.util.List;

import com.ithxh.baseCore.model.Pager;
import com.ithxh.xhh.constant.StaticConst.AndOrOr;

//insert delete update find
public interface BaseDao<T,V> {
	
	public int insert(T obj)throws SQLException;
	
	public int insertList(List<T> list)throws SQLException;
	
	public int delete(String id)throws SQLException;
	
	public int delete(String[] ids)throws SQLException;
	
	public int deleteByOneField(String fieldName,String value)throws SQLException;
	
	public int deleteByFields(String[] fieldName,String[] values);
	
	public int updateTable(T obj)throws SQLException;
	
	public int updateTableOneField(Object table,String fieldName,Object fieldValue,Object id)throws SQLException;
	
	public int updateTableOneField(String[] fieldName,Object[] fieldValue,Object id)throws SQLException;
	
	public int updateTableOneFieldAddValue(Object table, String fieldName,String id, Integer value)throws SQLException;
	
	public int updateTableOneFieldAddOne(Object table, String fieldName, String id)throws SQLException;
	
	public V findById(String id)throws SQLException;
	
	public V findByFieldName(String fieldName,String fieldValue)throws SQLException;
	
	public List<V> findListByIds(String[] ids)throws SQLException;
	
	public List<V> findListByFieldName(String fieldName,String fieldValue,boolean isSpecific)throws SQLException;
	
	public Pager findForPager(Pager pager, AndOrOr andOrOr);
	
	public List<V> findList(String sql, Object[] args);
	
	public List<V> findAll();
	
	public List<V> findByOrderAndLength(String order,String start,String end);
	
	public List<V> findByFields(String[] fields,Object[] values,AndOrOr andOrOr);
	
}
