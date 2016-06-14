package com.ithxh.xhh.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.StringUtil;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.constant.StaticConst;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;
import com.ithxh.xhh.service.BookUserService;
import com.ithxh.xhh.vo.formbean.BookUserVo;

@Controller
@RequestMapping("operatingBack")
public class OperatingBackCtrl extends BaseController{

	@Autowired
	private BookUserService bookUserService;
	
	
	/**
	 * @Description: 登录界面  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午12:41:10
	 * @param @return
	 */
	@RequestMapping(value="toLogin",method=RequestMethod.GET)
	public String toLogin(){
		this.removeSession(StaticConst.BACK_LOGIN_ACCOUNT);
		return "operating/login";
	}
	
	/**
	 * @Description: 进入后台首页  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午1:40:53
	 * @param @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toIndex() {
		BookUserVo bookUserVo = (BookUserVo) this.getBackUserVo();
		//判断用户是否拥有权限
		if(bookUserVo!=null && !ListUtil.isEmpty(bookUserVo.getpList())){
			return "operating/index";
		}else {
			return "redirect:/operatingBack/toLogin";
		}
		
	}
	
	/**
	 * @Description: 主体界面  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午12:40:22
	 * @param @return
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String toMain() {
         
		return "operating/main";
	}
	
	/**
	 * @Description: 默认界面  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午12:40:41
	 * @param @return
	 */
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String toDefault() {

		return "operating/default";
	}
	
	/**
	 * @Description: 登录  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午1:40:45
	 * @param @param userName
	 * @param @param password
	 * @param @param captcha
	 * @param @return
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	@ResponseBody
	public ReturnMessage<BookUserVo> doLogin(@RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam("captcha") String captcha) {

		ReturnMessage<BookUserVo> rm = new ReturnMessage<BookUserVo>();

		if (StringUtil.isNullAndBlank(userName)) {
			return ReturnMessage.get(ReturnMsg.BASE_FALSE, "请输入账号或邮箱");
		}

		if (StringUtil.isNullAndBlank(password)) {
			return ReturnMessage.get(ReturnMsg.BASE_FALSE, "请输入密码");
		}

		if (StringUtil.isNullAndBlank(captcha)) {
			return ReturnMessage.get(ReturnMsg.BASE_FALSE, "验证码不能为空");
		}
		
		// 检查验证码
		if (StringUtil.isNullAndBlank(captcha) || !captcha.equalsIgnoreCase(this.getRandCode())) {
			return ReturnMessage.get(ReturnMsg.BASE_FALSE, "验证码错误");
		}

		rm = bookUserService.login(userName, password);
		if (rm.isResult()) {
			if(ListUtil.isEmpty(rm.getO().getpList())){
				rm.setMessage("您没有权限登录该系统");
				rm.setResult(false);  // 表示登录成功
				return rm;
			}
			this.setSession(StaticConst.BACK_LOGIN_ACCOUNT,rm.getO());
		}
		
		return rm;
	}

	/**
	 * @Description: 退出登录  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午1:40:36
	 * @param @return
	 */
	@RequestMapping(value = "/doLogout", method = RequestMethod.GET)
	public String doLogout() {
		this.removeSession(StaticConst.BACK_LOGIN_ACCOUNT);
		removeRequest();
		removeResponse();
		return "redirect:" + getBasePath() + "/operatingBack/toLogin";
	}
}
