package com.ithxh.xhh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.dao.BookCollectDao;
import com.ithxh.xhh.dao.BookDao;
import com.ithxh.xhh.dao.BookUserDao;
import com.ithxh.xhh.dao.UploadFileDao;
import com.ithxh.xhh.entity.BookCollect;
import com.ithxh.xhh.exception.SystemBusyException;
import com.ithxh.xhh.service.BookCollectService;
import com.ithxh.xhh.vo.formbean.BookCollectVo;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Service("bookCollectService")
@Transactional
public class BookCollectServiceImpl extends BaseServiceImpl<BookCollect, BookCollectVo> implements BookCollectService{
	
	@Autowired
	BookUserDao bookUserDao;
	
	@Autowired
	UploadFileDao uploadFileDao;
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	BookCollectDao bookCollectDao;

	public ReturnMessage<Object> getPager(Pager pagerTemp,String userid) {
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		List<BookVo> list = bookCollectDao.findBookVoListPager(pagerTemp,userid);
		int total = bookCollectDao.findCount4Pager(userid);
		if(ListUtil.isEmpty(list)){
			rm.setResult(false);
			rm.setMessage("没有数据");
			return rm;
		}
		List<ArrayList<BookVo>> resultList = new ArrayList<ArrayList<BookVo>>();
		List<String> dateList = new ArrayList<String>();
		//按天书组装lsit
		try {
			for(BookVo vo : list){
				UploadFileVo pic = uploadFileDao.findById(vo.getBookpic());
				vo.setBookpicpath(pic.getUploadfilepath());
				int index = dateList.indexOf(vo.getBookaddtime());
				if(index==-1){
					ArrayList<BookVo> li = new ArrayList<BookVo>();
					li.add(vo);
					resultList.add(li);
					dateList.add(vo.getBookaddtime());
				}else{
					resultList.get(index).add(vo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemBusyException(e);
		}
		pagerTemp.setList(resultList);
		pagerTemp.setTotalCount(total);
		rm.setPager(pagerTemp);
		rm.setResult(true);
		return rm;
	}

	
	
}
