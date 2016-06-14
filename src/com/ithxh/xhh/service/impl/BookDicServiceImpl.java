package com.ithxh.xhh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.xhh.dao.BookDicDao;
import com.ithxh.xhh.entity.BookDic;
import com.ithxh.xhh.service.BookDicService;
import com.ithxh.xhh.vo.formbean.BookDicVo;

@Service("bookDicService")
@Transactional
public class BookDicServiceImpl extends BaseServiceImpl<BookDic, BookDicVo> implements BookDicService{

	@Autowired
	BookDicDao bookDicDao;
	
	public List<BookDicVo> getListByType(String type){
		return bookDicDao.getListByType(type);
	}
	
	
	public List<BookDicVo> getListByFatherId(String fatherId){
		List<BookDicVo> list = new ArrayList<BookDicVo>();
		try {
			list = bookDicDao.findListByFieldName("DICREMARK", fatherId,false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
