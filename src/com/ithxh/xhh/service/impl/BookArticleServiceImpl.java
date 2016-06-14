package com.ithxh.xhh.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.dao.BookArticleDao;
import com.ithxh.xhh.dao.BookUserDao;
import com.ithxh.xhh.dao.UploadFileDao;
import com.ithxh.xhh.entity.BookArticle;
import com.ithxh.xhh.service.BookArticleService;
import com.ithxh.xhh.service.BookUserService;
import com.ithxh.xhh.vo.formbean.BookArticleVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Service("bookArticleService")
@Transactional
public class BookArticleServiceImpl extends BaseServiceImpl<BookArticle, BookArticleVo> implements BookArticleService{
	
	@Autowired
	BookArticleDao bookArticleDao;
	
	@Autowired
	BookUserDao bookUserDao;
	
	@Autowired
	BookUserService bookUserService;
	
	@Autowired
	UploadFileDao uploadFileDao;

	@Override
	public BookArticleVo getById(String id) {
		//修改访问量
		BookArticleVo obj = null;
		try {
			bookArticleDao.updateTableOneFieldAddOne("book_article", "ARTICLEPV", id);
			obj = super.getById(id);
			UploadFileVo pic = uploadFileDao.findById(obj.getArticlepic());
			obj.setPicpath(pic.getUploadfilepath());
			BookUserVo user = bookUserService.getById(obj.getArticleuserid());
			obj.setUserVo(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public ReturnMessage<Object> getPager(Pager pagerTemp) {
		
		ReturnMessage<Object> rm = super.getPager(pagerTemp);
		List<?> list = rm.getPager().getList();
		
		if(ListUtil.isEmpty(list)){
			return rm;
		}
		
		for (int i = 0; i < list.size(); i++) {
			BookArticleVo obj = (BookArticleVo) list.get(i);
			try {
				UploadFileVo pic = uploadFileDao.findById(obj.getArticlepic());
				obj.setPicpath(pic.getUploadfilepath());
				BookUserVo user = bookUserService.getById(obj.getArticleuserid());
				obj.setUsername(user.getUsername());
				obj.setUserVo(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rm;
	}

	@Override
	public List<BookArticleVo> getByOrderAndLength(String order, String start,
			String end) {
		
		List<BookArticleVo> list = super.getByOrderAndLength(order, start, end);
		if(ListUtil.isEmpty(list)){
			return null;
		}
		try {
			for(BookArticleVo vo : list){
				UploadFileVo pic = uploadFileDao.findById(vo.getArticlepic());
				vo.setPicpath(pic.getUploadfilepath());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
