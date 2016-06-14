package com.ithxh.baseCore.baseInterface;

import java.util.List;

import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.StaticConst.AndOrOr;

//add del 
public interface BaseService<T,V> {

	public ReturnMessage<Object> add(V obj);
	
	public ReturnMessage<Object> add(List<V> list);
	
	public ReturnMessage<Object> del(String id);
	
	public ReturnMessage<Object> del(String[] ids);
	
	public ReturnMessage<Object> update(V obj);
	
	public V getById(String id);
	
	public V getByFieldName(String fieldName,String fieldValue);
	
	public List<V> getListByIds(String[] ids);
	
	public List<V> getListByFieldName(String fieldName,String fieldValue,boolean isSpecific);
	
	public ReturnMessage<Object> updateOneField(Object table, String fieldName,
			Object fieldValue, Object id);
	
	public ReturnMessage<Object> updateOneFieldAddValue(Object table, String fieldName,
			String id, Integer value);
	
	public ReturnMessage<Object> updateOneFieldAddOne(Object table, String fieldName, String id);
	
	public ReturnMessage<Object> getPager(Pager pagerTemp);
	
	public List<V> getAll();
	
	public List<V> getByOrderAndLength(String order,String start,String end);
	
	public List<V> getByFields(String[] fields,Object[] values,AndOrOr andOrOr);

}
