package com.ithxh.xhh.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ithxh.baseCore.baseInterface.impl.BaseServiceImpl;
import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.dao.BookAddressDao;
import com.ithxh.xhh.dao.BookBuyDao;
import com.ithxh.xhh.dao.BookDao;
import com.ithxh.xhh.dao.BookSellDao;
import com.ithxh.xhh.dao.BookUserDao;
import com.ithxh.xhh.dao.UploadFileDao;
import com.ithxh.xhh.entity.BookBuy;
import com.ithxh.xhh.exception.BaseException;
import com.ithxh.xhh.service.BookBuyService;
import com.ithxh.xhh.service.BookPocketService;
import com.ithxh.xhh.service.BookService;
import com.ithxh.xhh.vo.formbean.BookBuyVo;
import com.ithxh.xhh.vo.formbean.BookPocketVo;
import com.ithxh.xhh.vo.formbean.BookSellVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

@Service("bookBuyService")
@Transactional
public class BookBuyServiceImpl extends BaseServiceImpl<BookBuy, BookBuyVo> implements BookBuyService{
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookUserDao bookUserDao;
	
	@Autowired
	UploadFileDao uploadFileDao;
	
	@Autowired
	BookSellDao bookSellDao;
	
	@Autowired
	BookBuyDao bookBuyDao;
	
	@Autowired
	BookAddressDao bookAddressDao;
	
	@Autowired
	BookPocketService bookPocketService;

	@Override
	public ReturnMessage<Object> getPager(Pager pagerTemp) {
		
		ReturnMessage<Object> rm = super.getPager(pagerTemp);
		
		List<?> list = rm.getPager().getList();
		
		if(ListUtil.isEmpty(list)){
			return rm;
		}
		try {
			for (int i = 0; i < list.size(); i++) {
				
				BookBuyVo obj = (BookBuyVo) list.get(i);
				BookUserVo userVo = bookUserDao.findById(obj.getUserid());
				obj.setUsername(userVo.getUsername());
				
				BookSellVo sellVo = bookSellDao.findById(obj.getSellid());
				
				BookVo bookVo = bookDao.findById(sellVo.getBookid());
				obj.setBookname(bookVo.getBookname());
				
				UploadFileVo pic = uploadFileDao.findById(bookVo.getBookpic());
				obj.setBookpic(pic.getUploadfilepath());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			rm.setResult(false);
		}
		
		return rm;
	}

	
	/**
	 * 提交订单
	 */
	public ReturnMessage<BookBuyVo> addOrder(String[] pocketids,String userid){
		ReturnMessage<BookBuyVo> rm = new ReturnMessage<BookBuyVo>();
		try {
			List<BookBuyVo> vos = new ArrayList<BookBuyVo>();
			List<BookBuy> list = new ArrayList<BookBuy>();
			for(String pocketid:pocketids){
				BookBuy vo = new BookBuy();
				BookBuyVo vo1 = new BookBuyVo();
				BookPocketVo pocketVo = bookPocketService.getById(pocketid);
				BookSellVo sellVo = bookSellDao.findById(pocketVo.getSellid());
				BookVo bookVo = bookService.getById(sellVo.getBookid());
				vo.setBuyid(IDGenerator.uuidGenerate());
				vo.setSellid(pocketVo.getSellid());
				vo.setUserid(userid);
				vo.setBuytime(DateUtils.getNowDate());
				vo.setBuyprice(sellVo.getPrice());
				vo.setBuynumber(pocketVo.getBooknum());
				vo.setBuytotalamount(vo.getBuyprice()*vo.getBuynumber());
				vo.setBuystatu("0");
				BeanUtils.copyProperties(vo1, vo);
				vo1.setBookVo(bookVo);
				list.add(vo);
				vos.add(vo1);
			}
			int i = bookBuyDao.insertList(list);
			if(i!=list.size()){
				throw new BaseException("生成订单失败，请稍后再试");
			}else{
				
				rm.setList(vos);
				rm.setResult(true);
				return rm;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rm.setResult(false);
		return rm;
	}
	
	/**
	 * @Description: 支付  
	 * @author: 何建辉
	 * @date 2016年4月4日 下午2:06:19
	 * @param @param id
	 * @param @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ReturnMessage<BookBuyVo> pay(String[] buyid,String addressid,String[] remark,String userid){
		ReturnMessage<BookBuyVo> rm = new ReturnMessage<BookBuyVo>();
		List userList = new ArrayList();
		try {
			//获取支付的订单列表
			List<BookBuyVo> list = bookBuyDao.findListByIds(buyid);
			//判断库存是否足够
			for(BookBuyVo vo : list){
				BookSellVo sellVo = bookSellDao.findById(vo.getSellid());
				BookVo bookVo = bookDao.findById(sellVo.getBookid());
				BookUserVo userVo = bookUserDao.findById(sellVo.getUserid());
				userList.add(userVo.getEmail());
				String num = sellVo.getNum();
				int kc = Integer.parseInt(num)-sellVo.getSellnum();
				if(kc < vo.getBuynumber()){
					rm.setResult(false);
					rm.setMessage("抱歉，《"+bookVo.getBookname()+"》该书库存不足， 该卖家目前库存剩下 "+kc+" 本");
					return rm;
				}
			}
			
			//修改状态
			for (int i = 0; i < list.size(); i++) {
				BookBuyVo vo = list.get(i);
				//修改
				String re = "";
				if(remark!=null && remark.length>i){
					re = remark[i];
				}
				int n = bookBuyDao.updateTableOneField(new String[]{"buytime","buystatu","bookaddressid","buyremark"}, new String[]{DateUtils.getNowDate(),"1",addressid,re}, vo.getBuyid());
				if(n<1){
					throw new BaseException("支付失败");
				}
				//从购物车清除
				BookPocketVo pocketVo = new BookPocketVo();
				pocketVo.setUserid(userid);
				pocketVo.setSellid(vo.getSellid());
				bookPocketService.del(pocketVo);
				
				//从book表减去库存
				BookSellVo sellVo = bookSellDao.findById(vo.getSellid());
				n = bookDao.updateTableOneFieldAddValue("book", "booknum", sellVo.getBookid(), vo.getBuynumber()*-1);
				if(n<1){
					throw new BaseException("支付失败");
				}
				//从出售表sell中减去数量
				n = bookSellDao.updateTableOneFieldAddValue("book_sell", "sellnum", sellVo.getSellid(), vo.getBuynumber());
				if(n<1){
					throw new BaseException("支付失败");
				}
			}
			//成功
			rm.setList(userList);
			rm.setResult(true);
			return rm;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rm.setResult(false);
		return rm;
	}
	
}
