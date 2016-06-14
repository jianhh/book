package com.ithxh.xhh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.constant.StaticConst.AndOrOr;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;
import com.ithxh.xhh.dao.BookDao;
import com.ithxh.xhh.dao.BookSellDao;
import com.ithxh.xhh.dao.UploadFileDao;
import com.ithxh.xhh.entity.BookPocket;
import com.ithxh.xhh.exception.SystemBusyException;
import com.ithxh.xhh.service.BookPocketService;
import com.ithxh.xhh.vo.formbean.BookPocketVo;
import com.ithxh.xhh.vo.formbean.BookSellVo;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Component("bookPocketService")
@Transactional
public class BookPocketServiceImpl extends BaseServiceImpl<BookPocket, BookPocketVo> implements BookPocketService{

	@Autowired
	BookDao bookDao;
	
	@Autowired
	UploadFileDao uploadFileDao;
	
	@Autowired
	BookSellDao bookSellDao;
	
	@Override
	public ReturnMessage<Object> add(BookPocketVo obj) {
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		try {
			//判断所出售的书是否存在
			BookSellVo sell = bookSellDao.findById(obj.getSellid());
			
			BookVo bookVo = bookDao.findById(sell.getBookid());
			if(bookVo==null){
				rm.setResult(false);
				rm.setMessage("对不起，不存在该书");
			}
			obj.setAddtime(DateUtils.getNowDateTime());
			obj.setPocketid(IDGenerator.uuidGenerate());
			List<BookPocketVo> list = super.findByFields(new String[]{"sellid","userid"}, new String[]{obj.getSellid(),obj.getUserid()}, AndOrOr.AND);
			if(ListUtil.isEmpty(list)){
				obj.setBooknum(1);
				rm = super.add(obj);
				rm.setO(bookVo);
				return rm;
			}
			
			//如果书袋中已经有该书了，则数量+1
			BookPocketVo vo = list.get(0);
			if(vo.getSellid().equals(obj.getSellid())){
				int i = super.updateTableOneFieldAddOne("", "booknum", vo.getPocketid());
				if(i<1){
					return ReturnMessage.get(ReturnMsg.BASE_FALSE,"加入书袋失败");
				}else{
					bookVo.setBooknum(vo.getBooknum()+1);
					bookVo.setSellid(obj.getSellid());
					rm.setO(bookVo);
					rm.setMessage("加入书袋成功");
					return rm;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemBusyException(e);
		}
		
		return ReturnMessage.get(ReturnMsg.BASE_FALSE,"加入书袋失败");
	}
	
	
	/**
	 * @Description: 移出书袋  
	 * @author: 何建辉
	 * @date 2016年3月26日 下午4:08:21
	 * @param @param obj
	 * @param @return
	 */
	public ReturnMessage<Object> del(BookPocketVo obj) {
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		try {
			BookSellVo sellVo = bookSellDao.findById(obj.getSellid());
			if(sellVo==null){
				rm.setResult(false);
				rm.setMessage("对不起，不存在该书本");
				return rm;
			}
			
			int i = super.deleteByFields(new String[]{"sellid","userid"}, new String[]{obj.getSellid(),obj.getUserid()});
			if(i<1){
				return ReturnMessage.get(ReturnMsg.BASE_FALSE,"移出书袋失败");
			}else{
				//
				BookVo bookVo = bookDao.findById(sellVo.getBookid());
				rm.setO(bookVo);
				bookVo.setSellid(obj.getSellid());
				rm.setResult(true);
				rm.setMessage("移除书袋成功");
				return rm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rm;
	}
	
	/**
	 * @Description: 获取该用户书袋中的书  
	 * @author: 何建辉
	 * @date 2016年3月26日 下午6:20:07
	 * @param @param userid
	 * @param @return
	 */
	public List<BookVo> getBookVoByUserid(String userid){
		List<BookPocketVo> list = super.getListByFieldName("userid", userid,false);
		List<BookVo> bookList = new ArrayList<BookVo>();
		if(!ListUtil.isEmpty(list)){
			
			try {
				for(BookPocketVo v:list){
					String id = v.getSellid();
					BookSellVo sellVo = bookSellDao.findById(id);
					
					BookVo vo = bookDao.findById(sellVo.getBookid());
					if(vo==null){
						continue;
					}
					vo.setSellid(v.getPocketid());
					vo.setBookoldprice(sellVo.getPrice());
					vo.setBookremark(v.getBooknum()*sellVo.getPrice()+"");
					UploadFileVo upload = uploadFileDao.findById(vo.getBookpic());
					vo.setBookpicpath(upload.getUploadfilepath());
					vo.setBooknum(v.getBooknum());
					bookList.add(vo);
				}
				return bookList;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
}
