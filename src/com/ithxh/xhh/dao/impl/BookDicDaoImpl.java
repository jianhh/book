package com.ithxh.xhh.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.BookDicDao;
import com.ithxh.xhh.entity.BookDic;
import com.ithxh.xhh.vo.formbean.BookDicVo;

@Repository("bookDicDao")
public class BookDicDaoImpl extends BaseDaoImpl<BookDic, BookDicVo> implements BookDicDao{

	public List<BookDicVo> getListByType(String type){
		String sql = "select * from "+sb.getTableName()+" where DICTYPE=? and DICREMARK is not null";
		return jt.query(sql, new String[]{type}, ParameterizedBeanPropertyRowMapper.newInstance(BookDicVo.class));
	}
	
}
