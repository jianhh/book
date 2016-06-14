package com.ithxh.xhh.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.xhh.dao.BookCollectDao;
import com.ithxh.xhh.entity.BookCollect;
import com.ithxh.xhh.vo.formbean.BookCollectVo;
import com.ithxh.xhh.vo.formbean.BookVo;

@Repository("bookCollectDao")
public class BookCollectDaoImpl extends BaseDaoImpl<BookCollect, BookCollectVo> implements BookCollectDao{
	
	public List<BookVo> findBookVoListPager(Pager pager,String userid){
		int start = (pager.getPageNumber()-1)*pager.getPageSize();
		String sql = "select b.bookid,b.bookname,b.bookpublish,b.bookpublishtime,b.booknum,b.bookintro,b.bookauthor,b.bookpic,b.bookoldprice,c.addtime as bookaddtime from book_collect c join book b on c.bookid = b.bookid where userid=? order by c.addtime desc limit ?,?";
		
		return jt.query(sql, new Object[]{userid,start,pager.getPageSize()}, ParameterizedBeanPropertyRowMapper.newInstance(BookVo.class));
	} 
	
	@SuppressWarnings("deprecation")
	public int findCount4Pager(String userid){
		String sql = "select COUNT(1) from book_collect c join book b on c.bookid = b.bookid where c.userid=?";
		
		return jt.queryForInt(sql, new Object[]{userid});
	}
}
