package com.ithxh.xhh.controller.front;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.annotation.NeedLogin;
import com.ithxh.baseCore.baseUtils.ErrorUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.constant.StaticConst;
import com.ithxh.xhh.service.BookAddressService;
import com.ithxh.xhh.service.BookBuyService;
import com.ithxh.xhh.service.BookCollectService;
import com.ithxh.xhh.service.BookDicService;
import com.ithxh.xhh.service.BookPocketService;
import com.ithxh.xhh.service.BookSellService;
import com.ithxh.xhh.service.BookService;
import com.ithxh.xhh.service.BookUserService;
import com.ithxh.xhh.vo.formbean.BookAddressVo;
import com.ithxh.xhh.vo.formbean.BookBuyVo;
import com.ithxh.xhh.vo.formbean.BookDicVo;
import com.ithxh.xhh.vo.formbean.BookSellVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;
import com.ithxh.xhh.vo.formbean.BookVo;

@Controller
@RequestMapping("user")
public class UserCtrl extends BaseController{
	
	@Autowired
	BookCollectService bookCollectService;
	
	@Autowired
	BookPocketService bookPocketService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookUserService bookUserService;
	
	@Autowired
	BookDicService bookDicService;
	
	@Autowired
	BookSellService bookSellService;
	
	@Autowired
	BookBuyService bookBuyService;
	
	@Autowired
	BookAddressService bookAddressService;
	
	@NeedLogin
	@RequestMapping(value="personal",method=RequestMethod.GET)
	public String index(@RequestParam(required=false,defaultValue="0") int index){
		//获取所有学校
		List<BookDicVo> schoolList = bookDicService.getListByType("school");
		this.set("sList", schoolList);
		BookUserVo uservo = (BookUserVo) getBackUserVo();
		//获取学院
		List<BookDicVo> collegeList = bookDicService.getListByFatherId(uservo.getSchoolid());
		this.set("cList", collegeList);
		
		this.set("index", index);
		//获取用户信息
		
		return "front/personal";
	}
	
	@NeedLogin
	@RequestMapping(value="getCollectPager",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> getPager(Pager pager){
		BookUserVo user = (BookUserVo) getBackUserVo();
		ReturnMessage<Object> rm = bookCollectService.getPager(pager,user.getUserid());
		return rm;
	}
	
	@NeedLogin
	@RequestMapping(value="getBuyPager",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> getBuyPager(Pager pager,@RequestParam String status){
		BookUserVo user = (BookUserVo) getBackUserVo();
		ReturnMessage<Object> rm = bookSellService.getBuyListPager(pager,user.getUserid());
		return rm;
	}
	
	@NeedLogin
	@RequestMapping(value="getSellPager",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> getSellPager(Pager pager,@RequestParam String status){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		BookUserVo user = (BookUserVo) getBackUserVo();
		if(status.equals("0")){
			rm = bookSellService.getSellPager(pager,user.getUserid());
		}else if (status.equals("1")) {
			rm = bookSellService.getSuccessSellPager(pager,user.getUserid());
		}else{
			rm = bookSellService.getSellPager(pager,user.getUserid());
		}
		
		return rm;
	}
	
	/**
	 * @Description: 删除收藏夹  
	 * @author: 何建辉
	 * @date 2016年4月3日 上午1:03:46
	 * @param @param id
	 * @param @return
	 */
	@NeedLogin
	@RequestMapping(value="delPocket",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> delPocket(@RequestParam String[] id){
		ReturnMessage<Object> rm = bookPocketService.del(id);
		return rm;
	}
	
	
	@NeedLogin
	@RequestMapping(value="pocket",method=RequestMethod.GET)
	public String pocket(){
		BookUserVo user = (BookUserVo) getBackUserVo();
		//获取用户购物车
		List<BookVo> list = bookPocketService.getBookVoByUserid(user.getUserid());
		this.set("pocketList", list);
		
		//获取该用户的书袋存到session中
		List<BookVo> li = bookPocketService.getBookVoByUserid(user.getUserid());
		if(!ListUtil.isEmpty(li)){
			this.setSession(StaticConst.BOOKPOCKET,li);
			this.setSession("bookpocketnum", li.size()+"");
		}else{
			this.removeSession(StaticConst.BOOKPOCKET);
			this.setSession("bookpocketnum", "0");
		}
		
		return "front/bookpocket";
	}
	
	@NeedLogin
	@RequestMapping(value="doOrder")
	public String doOrder(@RequestParam String[] id){
		BookUserVo userVo = (BookUserVo) getBackUserVo();
		if(id==null || id.length==0){
			return "error/error";
		}
		ReturnMessage<BookBuyVo> rm = bookBuyService.addOrder(id, userVo.getUserid());
		if(rm.isResult()){
			//把订单列表存到域中
			setSession("orders", rm.getList());
			return "redirect:/user/toOrder";
		}else{
			return "error/error";
		}
		
	}
	
	@NeedLogin
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="toOrder",method=RequestMethod.GET)
	public String toOrder() throws IllegalAccessException, InvocationTargetException{
		List list = (List) getRequest().getSession().getAttribute("orders");
		double d = 0;
		for (int i = 0; i < list.size(); i++) {
			BookBuyVo obj = (BookBuyVo) list.get(i);
			d += obj.getBuytotalamount();
		}
		this.set("total", d);
		this.set("orderList",list);
		//获取地址
		//获取用户的收货地址列表
		BookUserVo userVo = (BookUserVo) getBackUserVo();
		List<BookAddressVo> li = bookAddressService.getListByFieldName("userid", userVo.getUserid(), false);
		
		if(!ListUtil.isEmpty(li)){
			for(BookAddressVo vo : li){
				BookAddressVo addressVo = bookAddressService.getById(vo.getAddressid());
				BeanUtils.copyProperties(vo, addressVo);
			}
			
			this.set("address", li.get(0));
		}else {
			this.set("address", new BookAddressVo());
		}
		this.set("list", li);
		return "front/order";
	}
	
	
	
	/**
	 * @Description: 出售  
	 * @author: 何建辉
	 * @date 2016年4月1日 下午4:49:44
	 * @param @param bookSellVo
	 * @param @param errors
	 * @param @return
	 */
	@NeedLogin
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="dosell",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> dosell(@Valid BookSellVo bookSellVo,Errors errors){
		
		ReturnMessage rm = ErrorUtils.checkErrors(errors);

		if (!rm.isResult()) {
			return rm;
		}
		BookUserVo uservo = (BookUserVo) getBackUserVo();
		bookSellVo.setUserid(uservo.getUserid());
		//出售
		rm = bookSellService.doSell(bookSellVo);
		
		return rm;
	}
	
	
	@RequestMapping(value="sell",method=RequestMethod.GET)
	public String sell(){
		
		return "front/sellbook";
	}
	
	@RequestMapping(value="toSell",method=RequestMethod.GET)
	public String toSell(@RequestParam String id){
		BookVo book = bookService.getById(id);
		if(book==null){
			return "error/error";
		}
		this.set("book", book);
		return "front/setDiscount4Sell";
	}
	
	/**
	 * @Description: 添加收货地址  
	 * @author: 何建辉
	 * @date 2016年4月3日 下午1:20:40
	 * @param @param vo
	 * @param @return
	 */
	@NeedLogin
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="editAddress",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> editAddress(@Valid BookAddressVo vo,Errors errors){
		ReturnMessage rm = ErrorUtils.checkErrors(errors);

		if (!rm.isResult()) {
			return rm;
		}
		if(StringUtil.isEmpty(vo.getAddressid())){
			//添加
			BookUserVo userVo = (BookUserVo) getBackUserVo();
			vo.setAddressid(IDGenerator.uuidGenerate());
			vo.setUserid(userVo.getUserid());
			rm = bookAddressService.add(vo);
		}else{
			//修改
			rm = bookAddressService.update(vo);
		}
		
		
		return rm;
	}
	
	/**
	 * @Description: 支付  
	 * @author: 何建辉
	 * @date 2016年4月4日 下午9:47:57
	 * @param @param id
	 * @param @param addressid
	 * @param @param remark
	 * @param @return
	 */
	@NeedLogin
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="pay",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<BookBuyVo> pay(@RequestParam String[] id,@RequestParam String addressid,@RequestParam String[] remark){
		
		BookUserVo userVo = (BookUserVo) getBackUserVo();
		
		ReturnMessage<BookBuyVo> rm = bookBuyService.pay(id, addressid, remark, userVo.getUserid());
		if(rm.isResult()){
			List list = rm.getList();
			//如果支付成功，邮件通知卖家
			sendBuyEmail((String)list.get(0),(String)list.get(0), "何小灰二手书-购买通知", "你好，你在何小灰二手书网站寄售的书籍已经有人购买，请注意查看。");
		}
		
		//获取该用户的书袋存到session中
		List<BookVo> li = bookPocketService.getBookVoByUserid(userVo.getUserid());
		if(!ListUtil.isEmpty(li)){
			this.setSession(StaticConst.BOOKPOCKET,li);
			this.setSession("bookpocketnum", li.size()+"");
		}else{
			this.removeSession(StaticConst.BOOKPOCKET);
			this.setSession("bookpocketnum", "0");
		}
		return rm;
	}
	
	/**
	 * @Description: 确认订单  
	 * @author: 何建辉
	 * @date 2016年4月4日 下午9:58:22
	 * @param @param id
	 * @param @return
	 */
	@NeedLogin
	@RequestMapping(value="confirmOrder",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> confirmOrder(@RequestParam String id){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		
		BookUserVo user = (BookUserVo) getBackUserVo();
		BookBuyVo buyVo = bookBuyService.getById(id);
		if(buyVo==null || buyVo.getBuystatu().equals("3")){
			rm.setResult(false);
			rm.setMessage("提交参数错误,请刷新页面在提交");
			return rm;
		}
		if(buyVo.getUserid().equals(user.getUserid())){
			rm = bookBuyService.updateOneField("book_buy", "BUYSTATU", "3", id);
		}else{
			rm.setResult(false);
			rm.setMessage("提交参数错误,请刷新页面在提交");
		}
		return rm;
	}
	
	@NeedLogin
	@RequestMapping(value="cancelOrder",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> cancelOrder(@RequestParam String id){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		
		BookUserVo user = (BookUserVo) getBackUserVo();
		BookBuyVo buyVo = bookBuyService.getById(id);
		if(buyVo==null){
			rm.setResult(false);
			rm.setMessage("提交参数错误,请刷新页面在提交");
			return rm;
		}
		
		if(buyVo.getUserid().equals(user.getUserid())){
			rm = bookBuyService.del(id);
		}else{
			rm.setResult(false);
			rm.setMessage("提交参数错误,请刷新页面在提交");
		}
		return rm;
	}
	
	@NeedLogin
	@RequestMapping(value="resetpwd",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> resetpwd(@RequestParam String newpwd,@RequestParam String oldpwd,@RequestParam String confirmpwd){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		
		if(!newpwd.equals(confirmpwd) || newpwd.length()<6 || newpwd.length()>16){
			rm.setResult(false);
			rm.setMessage("确认密码要跟新密码一致，并且在6-16个字符之间");
			return rm;
		}
		
		BookUserVo uservo = (BookUserVo) getBackUserVo();
		BookUserVo user = bookUserService.getById(uservo.getUserid());
		if(!user.getPassword().equals(oldpwd)){
			rm.setResult(false);
			rm.setMessage("旧密码错误，请重新输入");
			return rm;
		}
		rm = bookUserService.updateOneField("book_user", "password", newpwd, uservo.getUserid());
		
		return rm;
	}
	
	/**
	 * @Description:  修改图像
	 * @author: 何建辉
	 * @date 2016年4月5日 上午3:53:24
	 * @param @param newpwd
	 * @param @param oldpwd
	 * @param @param confirmpwd
	 * @param @return
	 */
	@NeedLogin
	@RequestMapping(value="changepic",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> changepic(@RequestParam String pic){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		
		BookUserVo uservo = (BookUserVo) getBackUserVo();
		rm = bookUserService.updateOneField("book_user", "userpic", pic, uservo.getUserid());
		if (rm.isResult()) {
			BookUserVo user = bookUserService.getById(uservo.getUserid());
			this.setSession(StaticConst.BACK_LOGIN_ACCOUNT,user);
		}
		return rm;
	}
	
	@NeedLogin
	@RequestMapping(value="toPay")
	public String toPay(@RequestParam String id){
		BookUserVo userVo = (BookUserVo) getBackUserVo();
		
		BookBuyVo vo = bookBuyService.getById(id);
		
		if(vo==null || !vo.getUserid().equals(userVo.getUserid())){
			return "error/error";
		}
		BookSellVo sellVo = bookSellService.getById(vo.getSellid());
		BookVo bookVo = bookService.getById(sellVo.getBookid());
		vo.setBookVo(bookVo);
		List<BookBuyVo> list = new ArrayList<BookBuyVo>();
		list.add(vo);
		//把订单列表存到域中
		setSession("orders", list);
		return "redirect:/user/toOrder";
		
	}
	
}
