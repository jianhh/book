package com.ithxh.xhh.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.ithxh.baseCore.baseInterface.impl.BaseDaoImpl;
import com.ithxh.xhh.dao.BookDao;
import com.ithxh.xhh.entity.Book;
import com.ithxh.xhh.vo.formbean.BookVo;

@Repository("bookDao")
public class BookDaoImpl extends BaseDaoImpl<Book, BookVo> implements BookDao{

	
	public List<BookVo> findBookVosByIsbnOrName(String isbnorname,int start,int length){
		String sql = "select * from "+sb.getTableName()+" where bookisbn like ? or bookname like ? or bookintro like ? or bookauthor like ? or bookpublish like ? limit ?,?";
		return jt.query(sql, new Object[]{"%"+isbnorname+"%","%"+isbnorname+"%","%"+isbnorname+"%","%"+isbnorname+"%","%"+isbnorname+"%",start,length}, ParameterizedBeanPropertyRowMapper.newInstance(voClass));
	}
	
	@SuppressWarnings("deprecation")
	public int findCountByIsbnOrName(String isbnorname){
		String sql = "select COUNT(1) from "+sb.getTableName()+" where bookisbn like ? or bookname like ? or bookintro like ? or bookauthor like ? or bookpublish like ?";
		return jt.queryForInt(sql, "%"+isbnorname+"%","%"+isbnorname+"%","%"+isbnorname+"%","%"+isbnorname+"%","%"+isbnorname+"%");
	}
}
