package com.ithxh.xhh.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.google.code.kaptcha.Constants;
import com.ithxh.baseCore.baseUtils.DateUtils;
import com.ithxh.baseCore.baseUtils.ListUtil;
import com.ithxh.baseCore.baseUtils.WebUtils;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.baseCore.plugins.smartupload.JspSmartUpload;
import com.ithxh.xhh.constant.StaticConst;
import com.ithxh.xhh.constant.StaticConst.ReturnMsg;
import com.ithxh.xhh.entity.UploadFile;
import com.ithxh.xhh.service.UploadFileService;
import com.ithxh.xhh.service.impl.EmailSender;
import com.ithxh.xhh.tools.SysConfigUtil;
import com.ithxh.xhh.vo.formbean.BookVo;
import com.ithxh.xhh.vo.formbean.UploadFileVo;

/**
 * @category 基础的controller 类，其它的controller都是从这里继承过去的
 * @author Administrator
 * 
 */
public class BaseController {
	
	@Autowired
	UploadFileService uploadFileService;
	
	@Autowired
	EmailSender emailSender;

	public static ThreadLocal<HttpServletRequest> Thread_Local_Request = new ThreadLocal<HttpServletRequest>();
	public static ThreadLocal<HttpServletResponse> Thread_Local_Response = new ThreadLocal<HttpServletResponse>();
	
	//---------------------------------------------------------------------
	
	/**
	 * @Description: 获取后台登录对象  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午12:31:24
	 * @param @return
	 */
	protected Object getBackUserVo(){
		return getSession().getAttribute(StaticConst.BACK_LOGIN_ACCOUNT);
	}
	
	/**
	 * @Description: 销毁登录的用户对象   
	 * @author: 何建辉
	 * @date 2016年1月15日 下午12:42:39
	 * @param @param key
	 */
	protected void removeSession(String key){
		HttpSession httpSession = getSession();
		if (httpSession != null) {
			httpSession.setAttribute(key, null);
			httpSession.removeAttribute(key);
		}
	}
	
	/**
	 * @Description: 保存到session中  
	 * @author: 何建辉
	 * @date 2016年1月15日 下午12:52:01
	 * @param @param key
	 * @param @param value
	 */
	protected void setSession(String key,Object value){
		getSession().setAttribute(key, value);
	}
	
	
	@SuppressWarnings("unchecked")
	protected int addBook2Pocket(BookVo vo){
		List<BookVo> list = (List<BookVo>) getSession().getAttribute(StaticConst.BOOKPOCKET);
		if(ListUtil.isEmpty(list)){
			list = new ArrayList<BookVo>();
		}
		boolean b = false;
		//如果书袋中已经有该书了，则数量+1
		for(BookVo v: list){
			if(vo.getBookid().equals(v.getBookid())){
				Integer booknum = v.getBooknum();
				if(booknum==null){
					v.setBooknum(2);
				}else{
					v.setBooknum(booknum+1);
				}
				b=true;
				break;
			}
		}
		//否则添加该书
		if(!b){
			vo.setBooknum(1);
			list.add(vo);
		}
		//重新刷新session中的书袋
		getSession().setAttribute(StaticConst.BOOKPOCKET, "");
		setSession(StaticConst.BOOKPOCKET, list);
		return list.size();
		
	}
	
	/**
	 * @Description:把书移除书袋  
	 * @author: 何建辉
	 * @date 2016年3月26日 下午2:47:31
	 * @param @param vo
	 */
	@SuppressWarnings("unchecked")
	protected int delBook4Pocket(BookVo vo){
		List<BookVo> list = (List<BookVo>) getSession().getAttribute(StaticConst.BOOKPOCKET);
		if(!ListUtil.isEmpty(list)){
			for(BookVo v: list){
				if(vo.getBookid().equals(v.getBookid())){
					Integer booknum = v.getBooknum();
					if(booknum==null || booknum==1){
						list.remove(v);
						break;
					}else{
						v.setBooknum(booknum-1);
					}
				}
			}
		}
		//重新刷新session中的书袋
		getSession().setAttribute(StaticConst.BOOKPOCKET, "");
		setSession(StaticConst.BOOKPOCKET, list);
		return list.size();
	}
	
	
	/**
	 * @Description:上传文件  
	 * @author: 何建辉
	 * @date 2016年3月26日 下午2:36:40
	 * @param @param request
	 * @param @param jsu
	 * @param @return
	 */
	protected ReturnMessage<UploadFile> uploadSimple1(HttpServletRequest request,JspSmartUpload jsu){
		
		//处理上传文件
		ReturnMessage<UploadFile> rm = new ReturnMessage<UploadFile>();

		List<MultipartFile> files = WebUtils.getList4Request(request);
		if(files==null){
			rm.setResult(false);
			return rm;
		}
		//验证文件
		rm = jsu.Validate(files);
		if(!rm.isResult()){
			return rm;
		}
		
		String realPath = getRealPath();  
		//初始化，转成UploadFile对象
		ReturnMessage<UploadFileVo> rmm = jsu.initUploadFile(request, realPath);
		if(!rmm.isResult()){
			rm.setResult(false);
			rm.setMessage(rmm.getMessage());
			return rm;
		}
		
		ReturnMessage<Object> returnMessage = uploadFileService.add(rmm.getList(),realPath);
		UploadFileVo vo = rmm.getList().get(0);
		UploadFile uf = new UploadFile();
		BeanUtils.copyProperties(vo, uf);
		rm.setO(uf);
		rm.setResult(returnMessage.isResult());
		rm.setMessage(returnMessage.getMessage());
		
		return rm;
	}
	
	protected ReturnMessage<UploadFile> uploadSimple(HttpServletRequest request,JspSmartUpload jsu){
		
		//处理上传文件
		ReturnMessage<UploadFile> rm = new ReturnMessage<UploadFile>();

		List<MultipartFile> files = WebUtils.getList4Request(request);
		if(files==null){
			rm.setResult(false);
			return rm;
		}
		//验证文件
		rm = jsu.Validate(files);
		if(!rm.isResult()){
			return rm;
		}
		String realPath = getRealPath();  
		//初始化，转成UploadFile对象
		ReturnMessage<UploadFileVo> rmm = jsu.initUploadFile(request, realPath);
		if(!rmm.isResult()){
			rm.setResult(false);
			rm.setMessage(rmm.getMessage());
			return rm;
		}
		ReturnMessage<Object> returnMessage = uploadFileService.add(rmm.getList(),realPath);
		UploadFileVo vo = rmm.getList().get(0);
		UploadFile uf = new UploadFile();
		BeanUtils.copyProperties(vo, uf);
		rm.setO(uf);
		rm.setResult(returnMessage.isResult());
		rm.setMessage(returnMessage.getMessage());
		
		return rm;
	}
	
	
	//------------------------------------------------------------------------------
	
	/**
	 * @category 获取HttpServletRequest
	 * @author z
	 * @date 2015-6-6
	 * @time 下午12:06:40
	 * @retrun HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {

		return Thread_Local_Request.get();
	}

	/**
	 * @category 设置HttpServletRequest
	 * @author z
	 * @date 2015-6-6
	 * @time 下午12:07:11
	 * @param request
	 * @retrun void
	 */
	public static void setRequest(HttpServletRequest request) {

		Thread_Local_Request.set(request);
	}

	/**
	 * @category 移除HttpServletRequest
	 * @author z
	 * @date 2015-5-19
	 * @time 下午6:20:10
	 * @return void
	 */
	public static void removeRequest() {

		Thread_Local_Request.remove();
	}

	/**
	 * @category 获取HttpServletResponse
	 * @author z
	 * @date 2015-5-19
	 * @time 下午6:17:11
	 * @return HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {

		return Thread_Local_Response.get();
	}

	/**
	 * @category 设置HttpServletResponse
	 * @author z
	 * @date 2015-6-6
	 * @time 下午12:07:43
	 * @param response
	 * @retrun void
	 */
	public static void setResponse(HttpServletResponse response) {

		Thread_Local_Response.set(response);
	}

	/**
	 * @category 移除HttpServletResponse
	 * @author z
	 * @date 2015-5-19
	 * @time 下午6:20:52
	 * @return void
	 */
	public static void removeResponse() {

		Thread_Local_Response.remove();
	}

	/**
	 * @category 获取HttpSession
	 * @author z
	 * @date 2015-5-20
	 * @time 下午4:48:45
	 * @return HttpSession
	 */
	private static HttpSession getSession() {

		if (getRequest() != null) {
			return getRequest().getSession();
		} else {
			return null;
		}
	}

	/**
	 * @category 获取项目真实路径
	 * @author z
	 * @date 2015-6-1
	 * @time 下午4:02:58
	 * @return String
	 */
	public static String getRealPath() {

		return getRequest().getSession().getServletContext().getRealPath("");
	}

	/**
	 * @category 设置返回给页面的属性
	 * @author z
	 * @date 2015-6-6
	 * @time 下午12:10:44
	 * @param key
	 *            属性名
	 * @param value
	 *            值
	 * @retrun void
	 */
	public void set(String key, Object value) {

		getRequest().setAttribute(key, value);
	}

	/**
	 * @category 设置页面不缓存
	 * @author z
	 * @date 2015-6-6
	 * @time 下午12:05:46
	 * @retrun void
	 */
	public static void setResponseNoCache() {

		getResponse().setHeader("progma", "no-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setHeader("Cache-Control", "no-store");
		getResponse().setDateHeader("Expires", 0);
	}
	
	/**
	 * @category 获取系统配置文件中的配置的 基础路径
	 * @author five
	 * @date 2015-6-6
	 * @time 下午12:08:37
	 * @retrun String
	 */
	public static String getBasePath() {

		return SysConfigUtil.getSysConfig().getSysBasePath();
	}

	/**
	 * @category 发送邮件激活二手书账号
	 * @param recipient         收件人
	 * @param emailAddress      收件人邮箱地址
	 * @param subject           邮件主题
	 * @param contents          邮件内容
	 * @retrun void
	 */
	public void sendEmail(String recipient, String emailAddress, String subject, String contents) {

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("email", recipient);
		hm.put("url", contents);
		hm.put("time", DateUtils.getNowDateTime());
		String template = "email/regActive.ftl";
		this.emailSender.sendSyncTemplateEmail(hm, subject, new String[] { emailAddress }, template);
	}
	
	/**
	 * @category 发送邮件通知卖家或买家
	 * @param recipient         收件人
	 * @param emailAddress      收件人邮箱地址
	 * @param subject           邮件主题
	 * @param contents          邮件内容
	 * @retrun void
	 */
	public void sendBuyEmail(String recipient, String emailAddress, String subject, String contents) {

		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("user", recipient);
		hm.put("content", contents);
		hm.put("time", DateUtils.getNowDateTime());
		String template = "email/remindTips.ftl";
		this.emailSender.sendSyncTemplateEmail(hm, subject, new String[] { emailAddress }, template);
	}
	
	public void sendSimpleEmail(String[] mailTo, String subject, String contents) {
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("email", mailTo);
		this.emailSender.sendSyncSimpleEmail(subject, mailTo, contents);
	}

	/**
	 * @category 获取验证码
	 * @author five 
	 * @date 2015-3-3
	 * @time 下午5:37:57
	 * @return String
	 */
	public String getRandCode() {

		HttpSession httpSession = getSession();
		if (httpSession != null) {
			return (String) httpSession.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ReturnMessage baserm(boolean b){
		if(b){
			return ReturnMessage.get(ReturnMsg.BASE_TRUE);
		}
		return ReturnMessage.get(ReturnMsg.BASE_FALSE);
	}
	
	@SuppressWarnings("rawtypes")
	public ReturnMessage baserm(boolean b,String message){
		if(b){
			return ReturnMessage.get(ReturnMsg.BASE_TRUE,message);
		}
		return ReturnMessage.get(ReturnMsg.BASE_FALSE,message);
	}
	
	
	@SuppressWarnings("rawtypes")
	public ReturnMessage grm(ReturnMsg msg){
		return ReturnMessage.get(msg);
	}
	
	@SuppressWarnings("rawtypes")
	public ReturnMessage grm(ReturnMsg msg,String message){
		return ReturnMessage.get(msg,message);
	}

}
