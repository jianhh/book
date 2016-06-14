package com.ithxh.xhh.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.BookBuyDao;
import com.ithxh.xhh.entity.BookBuy;
import com.ithxh.xhh.vo.formbean.BookBuyVo;

@Component("bookBuyDao")
public class BookBuyDaoImpl extends BaseDaoImpl<BookBuy, BookBuyVo> implements BookBuyDao{

	public List<BookBuyVo> findSuccessSellListByUserId(String userid,int start,int length){
		String sql = "select b.* from book_buy b join book_sell s on b.sellid=s.sellid where s.userid=? and b.buystatu!='0' order by buytime desc limit ?,?";
		return jt.query(sql, new Object[]{userid,start,length}, ParameterizedBeanPropertyRowMapper.newInstance(BookBuyVo.class));
	}
	
	@SuppressWarnings("deprecation")
	public int findCountSuccessSellListByUserId(String userid){
		String sql = "select COUNT(1) from book_buy b join book_sell s on b.sellid=s.sellid where b.userid=?";
		return jt.queryForInt(sql,userid);
	}
	
	public List<BookBuyVo> findBuyListByUserId(String userid,int start,int length){
		String sql = "select b.* from book_buy b join book_sell s on b.sellid=s.sellid where b.userid=? order by buytime desc limit ?,?";
		return jt.query(sql, new Object[]{userid,start,length}, ParameterizedBeanPropertyRowMapper.newInstance(BookBuyVo.class));
	}
}
