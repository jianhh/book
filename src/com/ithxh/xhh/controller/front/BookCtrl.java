package com.ithxh.xhh.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.annotation.NeedLogin;
import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.constant.StaticConst;
import com.ithxh.xhh.constant.StaticConst.AndOrOr;
import com.ithxh.xhh.service.BookBuyService;
import com.ithxh.xhh.service.BookCollectService;
import com.ithxh.xhh.service.BookCommentService;
import com.ithxh.xhh.service.BookPocketService;
import com.ithxh.xhh.service.BookSellService;
import com.ithxh.xhh.service.BookService;
import com.ithxh.xhh.service.BookUserService;
import com.ithxh.xhh.vo.formbean.BookBuyVo;
import com.ithxh.xhh.vo.formbean.BookCollectVo;
import com.ithxh.xhh.vo.formbean.BookCommentVo;
import com.ithxh.xhh.vo.formbean.BookPocketVo;
import com.ithxh.xhh.vo.formbean.BookSellVo;
import com.ithxh.xhh.vo.formbean.BookTypeVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;
import com.ithxh.xhh.vo.formbean.BookVo;

@Controller
@RequestMapping("book")
public class BookCtrl extends BaseController{
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookCommentService bookCommentService;
	
	@Autowired
	BookCollectService bookCollectService;

	@Autowired
	BookPocketService bookPocketService;
	
	@Autowired
	BookSellService bookSellService;
	
	@Autowired
	BookBuyService bookBuyService;
	
	@Autowired
	BookUserService bookUserService;
	
	/**
	 * @Description: 对分类列表进行组装成树结构
	 * @author: 何建辉
	 * @date 2016年4月5日 上午3:15:05
	 * @param @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		//获取所有分类
		List<BookTypeVo> list = bookService.getBooktypeList();
		this.set("list", list);
		return "front/booklist";
	}
	
	
	@RequestMapping(value="getPager",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> getPager(Pager pager){
		ReturnMessage<Object> rm = bookService.getPager(pager);
		return rm;
	}
	
	@RequestMapping(value="getCommentPager",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> getCommentPager(Pager pager){
		ReturnMessage<Object> rm = bookCommentService.getPager(pager);
		return rm;
	}
	
	
	/**
	 * @Description:获取该书的卖家列表分页  
	 * @author: 何建辉
	 * @date 2016年4月2日 下午11:11:44
	 * @param @param pager
	 * @param @param bookid
	 * @param @return
	 */
	@RequestMapping(value="getSellerPager",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> getSellerPager(Pager pager,@RequestParam String bookid){
		ReturnMessage<Object> rm = bookSellService.getSellerList(pager, bookid);
		return rm;
	}
	
	/**
	 * @Description: 书本详情页面  
	 * @author: 何建辉
	 * @date 2016年4月14日 下午1:58:56
	 * @param @param id
	 * @param @return
	 */
	@RequestMapping(value="detail/{id}",method=RequestMethod.GET)
	public String detail(@PathVariable String id){
		BookVo vo = bookService.getById(id);
		if(vo==null){
			return StaticConst.ERROR_PAGE;
		}
		this.set("obj", vo);
		//评论
		List<BookCommentVo> list = bookCommentService.getByOrderAndLength(" COMMENTTIME desc ", "0", "10");
		this.set("commentList", list);
		//右侧4个
		List<BookVo> bli = bookService.getByOrderAndLength(" RAND() ", "0", "4");
		List<BookVo> blist = bookService.getByOrderAndLength(" RAND() ", "0", "4");
		
		BookUserVo user = (BookUserVo) getBackUserVo();
		if(user!=null){
			//是否收藏
			List<BookCollectVo> booklist = bookCollectService.getByFields(new String[]{"bookid","userid"}, new String[]{id,user.getUserid()}, AndOrOr.AND);
			if(!ListUtil.isEmpty(booklist)){
				this.set("collect", "true");
			}
		}
		this.set("li1", bli);
		this.set("li2", blist);
		
		return "front/detail";
	}
	
	@NeedLogin
	@RequestMapping(value="doComment",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> doComment(BookCommentVo vo,@RequestParam String checkcode){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		
		if(StringUtil.isEmpty(vo.getCommentcontent())){
			rm.setResult(false);
			rm.setMessage("内容不能为空");
			return rm;
		}
		if(vo.getCommentcontent().trim().length()<=5){
			rm.setResult(false);
			rm.setMessage("内容不能少于5个字符");
			return rm;
		}
		
		if(StringUtil.isEmpty(checkcode)){
			rm.setResult(false);
			rm.setMessage("验证码不能为空");
			return rm;
		}
		if(!checkcode.equalsIgnoreCase(getRandCode())){
			rm.setResult(false);
			rm.setMessage("验证码不正确");
			return rm;
		}
		BookUserVo uservo = (BookUserVo) getBackUserVo();
		
		vo.setCommentid(IDGenerator.uuidGenerate());
		vo.setCommenttime(DateUtils.getNowDateTime());
		vo.setCommentuserid(uservo.getUserid());
		rm = bookCommentService.add(vo);
		return rm;
	}
	
	@NeedLogin
	@ResponseBody
	@RequestMapping(value="tobookpocket",method=RequestMethod.POST)
	public ReturnMessage<Object> toBookPocket(@RequestParam String id){
		//查询是否存在该书
		BookUserVo user = (BookUserVo) getBackUserVo();
		BookPocketVo bp = new BookPocketVo();
		bp.setSellid(id);
		bp.setUserid(user.getUserid());
		ReturnMessage<Object> rm = bookPocketService.add(bp);
		
		//加入session书袋
		if(rm.isResult()){
			BookVo vo = (BookVo) rm.getO();
			int num = addBook2Pocket(vo);
			rm.setState(num+"");
			this.setSession("bookpocketnum", num+"");
		}
		return rm;
	}
	
	@NeedLogin
	@ResponseBody
	@RequestMapping(value="removebook4pocket",method=RequestMethod.POST)
	public ReturnMessage<Object> removeBook4Pocket(@RequestParam String id){
		BookUserVo user = (BookUserVo) getBackUserVo();
		BookPocketVo bp = new BookPocketVo();
		bp.setSellid(id);
		bp.setUserid(user.getUserid());
		ReturnMessage<Object> rm = bookPocketService.del(bp);
		
		//从session书袋中移出该书
		if(rm.isResult()){
			BookVo book = (BookVo) rm.getO();
			int num = delBook4Pocket(book);
			rm.setState(num+"");
			this.setSession("bookpocketnum", num);
		}
		return rm;
	}
	
	@NeedLogin
	@RequestMapping(value="collect",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> collect(@RequestParam String id){
		BookUserVo user = (BookUserVo) getBackUserVo();
		ReturnMessage<Object> rm = bookService.collect(id, user.getUserid());
		return rm;
	}
	
	/**
	 * @Description: 上架  
	 * @author: 何建辉
	 * @date 2016年4月5日 上午12:38:13
	 * @param @param id
	 * @param @return
	 */
	@NeedLogin
	@RequestMapping(value="putbook",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> putbook(@RequestParam String id){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		BookUserVo user = (BookUserVo) getBackUserVo();
		BookSellVo sellVo = bookSellService.getById(id);
		if(sellVo==null || !user.getUserid().equals(sellVo.getUserid()) || "0".equals(sellVo.getSellStatus())){
			rm.setResult(false);
			rm.setMessage("提交参数错误，请刷新页面再提交");
			return rm;
		}
		rm = bookSellService.updateOneField("book_sell", "sellstatus", "0", id);
		return rm;
	}
	
	/**
	 * @Description: 下架  
	 * @author: 何建辉
	 * @date 2016年4月5日 上午12:38:18
	 * @param @param id
	 * @param @return
	 */
	@NeedLogin
	@RequestMapping(value="outbook",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> outbook(@RequestParam String id){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		BookUserVo user = (BookUserVo) getBackUserVo();
		BookSellVo sellVo = bookSellService.getById(id);
		if(sellVo==null || !user.getUserid().equals(sellVo.getUserid()) || "1".equals(sellVo.getSellStatus())){
			rm.setResult(false);
			rm.setMessage("提交参数错误，请刷新页面再提交");
			return rm;
		}
		rm = bookSellService.updateOneField("book_sell", "sellstatus", "1", id);
		return rm;
	}
	
	/**
	 * @Description: 修改从已发货
	 * @author: 何建辉
	 * @date 2016年4月5日 上午12:38:18
	 * @param @param id
	 * @param @return
	 */
	@NeedLogin
	@RequestMapping(value="setbuystatus",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> setbuystatus(@RequestParam String id){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		BookUserVo user = (BookUserVo) getBackUserVo();
		BookBuyVo buyVo = bookBuyService.getById(id);
		BookSellVo sellVo = bookSellService.getById(buyVo.getSellid());
		if(sellVo==null || !user.getUserid().equals(sellVo.getUserid()) || !"1".equals(buyVo.getBuystatu())){
			rm.setResult(false);
			rm.setMessage("提交参数错误，请刷新页面再提交");
			return rm;
		}
		rm = bookBuyService.updateOneField("book_buy", "buystatu", "2", id);
		if(rm.isResult()){
			BookUserVo userVo = bookUserService.getById(buyVo.getUserid());
			sendBuyEmail(userVo.getUsername(),userVo.getEmail(), "何小灰二手书-发货通知", "您好，你所购买的书籍已经发货，请注意查看。");
		}
		return rm;
	}
	
	/**
	 * @Description: 修改折扣等
	 * @author: 何建辉
	 * @date 2016年4月5日 上午12:38:18
	 * @param @param id
	 * @param @return
	 */
	@NeedLogin
	@RequestMapping(value="setbook",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> setbook(@RequestParam String id,@RequestParam int num){
		ReturnMessage<Object> rm = new ReturnMessage<Object>();
		BookUserVo user = (BookUserVo) getBackUserVo();
		BookSellVo sellVo = bookSellService.getById(id);
		if(sellVo==null || !user.getUserid().equals(sellVo.getUserid()) || num<1){
			rm.setResult(false);
			rm.setMessage("提交参数错误，请刷新页面再提交");
			return rm;
		}
		
		rm = bookSellService.updateOneField("book_sell", "num", num, id);
		return rm;
	}
	
}
