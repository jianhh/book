package com.ithxh.xhh.controller.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.IDGenerator;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.Pager;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.constant.StaticConst;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;
import com.ithxh.xhh.service.BookArticleService;
import com.ithxh.xhh.service.BookDicService;
import com.ithxh.xhh.service.BookPocketService;
import com.ithxh.xhh.service.BookService;
import com.ithxh.xhh.service.BookUserService;
import com.ithxh.xhh.service.BookValidService;
import com.ithxh.xhh.tools.SysConfigUtil;
import com.ithxh.xhh.vo.formbean.BookArticleVo;
import com.ithxh.xhh.vo.formbean.BookDicVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;
import com.ithxh.xhh.vo.formbean.BookValidVo;
import com.ithxh.xhh.vo.formbean.BookVo;

@Controller
@RequestMapping("index")
public class IndexCtrl extends BaseController{
	
	@Autowired
	BookPocketService bookPocketService;
	
	@Autowired
	BookUserService bookUserService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookArticleService bookArticleService;
	
	@Autowired
	BookValidService bookValidService;
	
	@Autowired
	BookDicService bookDicService;
	
	@RequestMapping(value="toReg",method=RequestMethod.GET)
	public String toReg(){
		//获取所有学校
		List<BookDicVo> schoolList = bookDicService.getListByType("school");
		this.set("sList", schoolList);
		return "front/register";
	}
	
	@RequestMapping(value="toLogin",method=RequestMethod.GET)
	public String toLogin(@RequestParam(value = "lastUrl", required = false) String lastUrl) {
		if(StringUtil.isNullAndBlank(lastUrl)){
			lastUrl = getRequest().getHeader("Referer");
		}
		
		if(lastUrl.substring(lastUrl.lastIndexOf("/")+1).indexOf("toReg")!=-1){
			lastUrl = getBasePath()+"/index";
		}
		
		this.set("lastUrl",lastUrl);
		return "front/login";
	}
	
	@RequestMapping(value = "/doLogout", method = RequestMethod.GET)
	public String doLogout() {
		this.removeSession(StaticConst.BACK_LOGIN_ACCOUNT);
		this.removeSession(StaticConst.BOOKPOCKET);
		this.removeSession("bookpocketnum");
		removeRequest();
		removeResponse();
		return "redirect:" + getBasePath() + "/index";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="doReg",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> doReg(BookUserVo userVo){
		
		if(!userVo.getPassword().trim().equals(userVo.getConfirmpassword().trim())){
			return baserm(false,"两次密码输入不一致，请重新输入");
		}
		
		if (StringUtil.isNullAndBlank(userVo.getCaptcha())) {
			return baserm(false,"验证码不能为空");
		}
		
		// 检查验证码
		if (StringUtil.isNullAndBlank(userVo.getCaptcha()) || !userVo.getCaptcha().equalsIgnoreCase(this.getRandCode())) {
			return baserm(false,"验证码错误");
		}
		
		ReturnMessage<Object> rm = bookUserService.doReg(userVo);
		
		return rm;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="doLogin",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<BookUserVo> doLogin(@RequestParam String username,@RequestParam String password){
		
		if (StringUtil.isNullAndBlank(username)) {
			return baserm(false, "请输入账号或邮箱");
		}

		if (StringUtil.isNullAndBlank(password)) {
			return baserm(false, "请输入密码");
		}
		//登录
		ReturnMessage<BookUserVo> rm = bookUserService.login(username, password);
		if(!rm.isResult()){
			return rm;
		}
		//保存信息到session
		BookUserVo user = rm.getO();
		if (rm.isResult()) {
			this.setSession(StaticConst.BACK_LOGIN_ACCOUNT,user);
		}
		//获取该用户的书袋存到session中
		List<BookVo> list = bookPocketService.getBookVoByUserid(user.getUserid());
		if(!ListUtil.isEmpty(list)){
			this.setSession(StaticConst.BOOKPOCKET,list);
			this.setSession("bookpocketnum", list.size()+"");
		}
		
		return rm;
	}
	
	@RequestMapping("")
	public String toIndex(){
		//获取新书
		List<BookVo> newList = bookService.getByOrderAndLength(" BOOKADDTIME desc ", "0", "8");
		this.set("newList", newList);
		//随机获取书籍
		List<BookVo> randList = bookService.getByOrderAndLength(" RAND() ", "0", "8");
		this.set("randList", randList);
		//获取公告
		List<BookArticleVo> adList = bookArticleService.getByOrderAndLength(" ARTICLETIME desc ", "0", "3");
		this.set("adList", adList);
/*		
		//获取公告
		List<BookArticleVo> myadList = bookArticleService.getByOrderAndLength(" ARTICLETIME desc ", "3", "3");
		this.set("myadList", myadList);
*/		
		return "front/index";
	}
	
	/**
	 * @Description: 公告
	 * @author: 何建辉
	 * @date 2016年4月14日 下午7:53:18
	 * @param @return
	 */
	@RequestMapping("ad")
	public String toAd(){
		
		/*//获取公告
		List<BookArticleVo> myadList = bookArticleService.getByOrderAndLength(" ARTICLETIME desc ", "3", "3");
		this.set("myadList", myadList);*/
		
		return "front/ad";
	}
	
	
	@RequestMapping(value="adetail/{id}",method=RequestMethod.GET)
	public String adetail(@PathVariable String id){
		BookArticleVo articleVo = bookArticleService.getById(id);
		this.set("ad", articleVo);
		return "front/adetail";
	}
	
	@RequestMapping(value="getAdPager",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> getAdPager(Pager pager){
		pager.setOrderBy(" ARTICLETIME desc ");
		pager.setPageSize(9);
		ReturnMessage<Object> rm = bookArticleService.getPager(pager);
		return rm;
	}
	
	@RequestMapping(value="isActive",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> isActive(@RequestParam String id){
		BookUserVo userVo = bookUserService.getById(id);
		if(userVo==null){
			return ReturnMessage.get(ReturnMsg.BASE_FALSE);
		}
		if(userVo.getIsactive().equals("0")){
			return ReturnMessage.get(ReturnMsg.BASE_FALSE);
		}else{
			return ReturnMessage.get(ReturnMsg.BASE_TRUE);
		}
	}
	
	@RequestMapping(value="sendEmail",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<Object> sendEmail(@RequestParam String email){
		
		String url = SysConfigUtil.getSysConfig().getSysBasePath()+"/index/validate/";
		String code = IDGenerator.uuidGenerate();
		BookValidVo validVo = new BookValidVo();
		validVo.setId(IDGenerator.uuidGenerate());
		validVo.setValidcode(code);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		validVo.setStarttime(sdf.format(calendar.getTime()));
		//30分钟
		calendar.add(Calendar.MINUTE, 30);
		validVo.setEndtime(sdf.format(calendar.getTime()));
		//获取账号
		BookUserVo userVo = bookUserService.getByFieldName("email", email);
		if(userVo==null){
			return ReturnMessage.get(ReturnMsg.BASE_FALSE,"对不起，发送邮件失败，请稍后再试");
		}
		validVo.setStatus("0");
		validVo.setUserid(userVo.getUserid());
		
		ReturnMessage<Object> rm = bookValidService.add(validVo);
		if(rm.isResult()){
			this.sendEmail(email, email, "注册账号邮箱激活-二手书网", url+code);
		}else{
			rm.setMessage("发送邮件失败，请稍后再试");
		}
		
		return rm;
	}
	
	
	@RequestMapping(value="validate/{code}")
	public String validate(@PathVariable String code) throws ParseException{
		
		ReturnMessage<Object> rm = bookUserService.validate(code);
		this.set("rm", rm);
		
		return "front/validate";
	}
	
	
	@RequestMapping(value="completeReg")
	@ResponseBody
	public ReturnMessage<Object> completeReg(BookUserVo vo){
		ReturnMessage<Object> rm = bookUserService.completeReg(vo);
		return rm;
	}
	
/*	
	@RequestMapping("detail")
	public String detail(){
		return "front/detail";
	}
	
	@RequestMapping("person")
	public String person(){
		return "front/personal";
	}
	
	@RequestMapping("search")
	public String search(){
		return "front/search";
	}
	
	
	@RequestMapping("order")
	public String order(){
		return "front/order";
	}
	
	@RequestMapping("confirmOrder")
	public String confirmOrder(){
		return "front/order-address";
	}
	
	@RequestMapping("booklist")
	public String booklist(){
		return "front/booklist";
	}
*/}
