package com.ithxh.xhh.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.BookSellDao;
import com.ithxh.xhh.entity.BookSell;
import com.ithxh.xhh.vo.formbean.BookSellVo;

@Component("bookSellDao")
public class BookSellDaoImpl extends BaseDaoImpl<BookSell, BookSellVo> implements BookSellDao{
	
	
	public List<BookSellVo> findSellListByUser(String userid,int start,int length){
		String sql = "select * from "+sb.getTableName()+" where userid=? order by selltime desc limit ?,?";
		return jt.query(sql, new Object[]{userid,start,length}, ParameterizedBeanPropertyRowMapper.newInstance(voClass));
	}
	
	@SuppressWarnings("deprecation")
	public int findCountSellListByUser(String userid){
		String sql = "select count(1) from "+sb.getTableName()+" where userid=? ";
		return jt.queryForInt(sql, userid);
	}
	
	public List<BookSellVo> findSellListByBookid(String bookid,int start,int length){
		String sql = "select * from " + sb.getTableName()+" where num>sellnum and sellstatus='0' and bookid=? order by selltime desc limit ?,? ";
		return jt.query(sql, new Object[]{bookid,start,length}, ParameterizedBeanPropertyRowMapper.newInstance(voClass));
	}
	
	@SuppressWarnings("deprecation")
	public int findCountSellByBookid(String bookid){
		String sql = "select sum(num-sellnum) from "+sb.getTableName()+" where bookid=? and sellstatus='0'";
		return jt.queryForInt(sql, bookid);
	}
}
