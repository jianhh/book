package com.ithxh.xhh.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.dao.BookDao;
import com.ithxh.xhh.dao.BookUserDao;
import com.ithxh.xhh.entity.BookComment;
import com.ithxh.xhh.service.BookCommentService;
import com.ithxh.xhh.service.UploadFileService;
import com.ithxh.xhh.vo.formbean.BookCommentVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Service("bookCommentService")
@Transactional
public class BookCommentServiceImpl extends BaseServiceImpl<BookComment, BookCommentVo> implements BookCommentService{

	@Autowired
	BookUserDao bookUserDao;
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	UploadFileService uploadFileService;
	
	
	@Override
	public ReturnMessage<Object> getPager(Pager pagerTemp) {
		
		ReturnMessage<Object> rm = super.getPager(pagerTemp);
		
		if(!rm.isResult()){
			return rm;
		}
		List<?> list = rm.getPager().getList();
		try {
			for (int i = 0; i < list.size(); i++) {
				BookCommentVo obj = (BookCommentVo) list.get(i);
				BookUserVo userVo = bookUserDao.findById(obj.getCommentuserid());
				obj.setUsername(userVo.getUsername());
				UploadFileVo file = uploadFileService.getById(userVo.getUserpic());
				obj.setUserpic(file.getUploadfilepath());
				BookVo bookVo = bookDao.findById(obj.getCommentbookid());
				obj.setBookname(bookVo.getBookname());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rm;
	}

	
	
}
