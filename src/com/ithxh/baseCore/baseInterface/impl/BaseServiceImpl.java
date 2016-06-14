package com.ithxh.baseCore.baseInterface.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.BaseService;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.StaticConst.AndOrOr;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;
import com.ithxh.xhh.exception.SystemBusyException;

public class BaseServiceImpl<T,V> extends BaseDaoImpl<T,V> implements BaseService<T,V> {
	
	Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	@Override
	public ReturnMessage<Object> add(V obj) {
		
		int i = 0;
		try {
			T t = entityClass.newInstance();
			BeanUtils.copyProperties(t, obj);
			i = super.insert(t);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		
		return i==1?ReturnMessage.get(ReturnMsg.ADD_SUCCESS):ReturnMessage.get(ReturnMsg.ADD_FAIL);
	}

	@Override
	public ReturnMessage<Object> add(List<V> list) {
		int i = 0;
		List<T> li = new ArrayList<T>();
		try {
			for(V v : list){
				T t = entityClass.newInstance();
				BeanUtils.copyProperties(t, v);
				li.add(t);
			}
			i = super.insertList(li);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		rm.setState(i+"");
		if(i==list.size()){
			return ReturnMessage.get(ReturnMsg.ADD_SUCCESS);
		}else {
			return rm;
		}
	}

	@Override
	public ReturnMessage<Object> del(String id) {
		int i = 0;
		try {
			i = super.delete(id);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
		return i==1?ReturnMessage.get(ReturnMsg.DELETE_SUCCESS):ReturnMessage.get(ReturnMsg.DELETE_FAIL);
	}

	@Override
	@Transactional
	public ReturnMessage<Object> del(String[] ids) {
		int i = 0;
		try {
			i += super.delete(ids);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
			throw new SystemBusyException(e);
		}
		return i==ids.length?ReturnMessage.get(ReturnMsg.DELETE_SUCCESS):ReturnMessage.get(ReturnMsg.DELETE_FAIL);
	}

	@Override
	public ReturnMessage<Object> update(V obj) {
		int i = 0;
		try {
			T t = entityClass.newInstance();
			BeanUtils.copyProperties(t, obj);
			i = super.updateTable(t);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return i==1?ReturnMessage.get(ReturnMsg.UPDATE_SUCCESS):ReturnMessage.get(ReturnMsg.UPDATE_FAIL);
	}

	@Override
	public V getById(String id) {
		V vo = null;
		try {
			vo = super.findById(id);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
		return vo;
	}

	@Override
	public V getByFieldName(String fieldName, String fieldValue) {
		V vo = null;
		try {
			vo = super.findByFieldName(fieldName, fieldValue);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
		return vo;
	}

	@Override
	public List<V> getListByIds(String[] ids) {
		List<V> obj = null;
		try {
			obj = super.findListByIds(ids);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
		return obj;
	}

	@Override
	public List<V> getListByFieldName(String fieldName, String fieldValue,
			boolean isSpecific) {
		List<V> obj = null;
		try {
			obj = super.findListByFieldName(fieldName,fieldValue,isSpecific);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
		return obj;
	}

	@Override
	public ReturnMessage<Object> updateOneField(Object table, String fieldName,
			Object fieldValue, Object id) {
		
		int i = 0;
		try {
			i = super.updateTableOneField(table, fieldName, fieldValue, id);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
		return i==1?ReturnMessage.get(ReturnMsg.UPDATE_SUCCESS):ReturnMessage.get(ReturnMsg.UPDATE_FAIL);
	}

	@Override
	public ReturnMessage<Object> updateOneFieldAddValue(Object table,
			String fieldName, String id, Integer value) {
		
		int i = 0;
		try {
			i = super.updateTableOneFieldAddValue(table, fieldName, id, value);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
		return i==1?ReturnMessage.get(ReturnMsg.UPDATE_SUCCESS):ReturnMessage.get(ReturnMsg.UPDATE_FAIL);
	}

	@Override
	public ReturnMessage<Object> updateOneFieldAddOne(Object table, String fieldName, String id) {
		
		int i = 0;
		try {
			i = super.updateTableOneFieldAddOne(table, fieldName, id);
		} catch (SQLException e) {
			log.error(e.getLocalizedMessage());
		}
		return i==1?ReturnMessage.get(ReturnMsg.UPDATE_SUCCESS):ReturnMessage.get(ReturnMsg.UPDATE_FAIL);
	}

	@Override
	public ReturnMessage<Object> getPager(Pager pagerTemp) {
		Pager pager;
		pager = this.findForPager(pagerTemp, AndOrOr.AND);
		if (pager.getList() != null && pager.getList().size() > 0) {
			ReturnMessage<Object> rm = ReturnMessage.get(ReturnMsg.BASE_TRUE);
			rm.setPager(pager);
			return rm;
		} else {
			return ReturnMessage.get(ReturnMsg.NOT_FOUND);
		}
	}

	@Override
	public List<V> getAll() {
		List<V> allList = super.findAll();
		return allList;
	}
	
	public List<V> getByOrderAndLength(String order,String start,String end){
		return super.findByOrderAndLength(order, start, end);
	}

	@Override
	public List<V> getByFields(String[] fields, Object[] values, AndOrOr andOrOr) {
		
		return super.findByFields(fields, values, andOrOr);
	}
	
}
