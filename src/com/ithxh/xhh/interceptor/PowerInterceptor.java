package com.ithxh.xhh.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ithxh.baseCore.annotation.NeedLogin;
import com.ithxh.baseCore.model.ReturnMessage;
import com.ithxh.xhh.base.BaseController;
import com.ithxh.xhh.constant.StaticConst;
import com.ithxh.xhh.service.BookPrivilegeService;
import com.ithxh.xhh.tools.SysConfigUtil;
import com.ithxh.xhh.vo.formbean.BookPrivilegeVo;
import com.ithxh.xhh.vo.formbean.BookUserVo;


/**
 * @category 执行权限验证
 * 
 */
public class PowerInterceptor implements HandlerInterceptor {
	
	Logger log = LoggerFactory.getLogger(PowerInterceptor.class);
	
	@Autowired
	BookPrivilegeService bookPrivilegeService;

	/**
	 * 在controller后拦截
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {

		BaseController.removeRequest();
		BaseController.removeResponse();

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		
	}

	/**
	 * 在controller前拦截
	 * false 表示
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		BaseController.setRequest(request);
		BaseController.setResponse(response);
		
		
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
			log.info("访问的地址："+request.getRequestURI());
			
			NeedLogin needLogin = ((HandlerMethod) handler).getMethodAnnotation(NeedLogin.class);
			if(needLogin!=null){
				//需要登录
				//判断是否已经登录
				BookUserVo user = (BookUserVo) request.getSession().getAttribute(StaticConst.BACK_LOGIN_ACCOUNT);
				if(user==null){
					needLogin(request, response, "/index/toLogin");
					return false;
				}
			}else{
				//判断是否需要权限操作
				String vname = request.getRequestURI();
				String visiturl = vname.substring(1);
				
				BookPrivilegeVo privilegeVo = bookPrivilegeService.getByFieldName("PRIVILEGEURL", visiturl);
				if(privilegeVo==null){
					return true;
				}else{
					//有，则需要权限，再判断是否登陆
					BookUserVo user = (BookUserVo) request.getSession().getAttribute("backuser");
					if(user==null){
						needLogin(request, response, "/operatingBack/toLogin");
						return false;
					}else{
						//从用户中取出用户所拥有的权限list
						List<BookPrivilegeVo> privilegelist = user.getpList();
						//如果privilegelist为空，则该用户没有权限列表
						if(privilegelist==null || privilegelist.size()==0){
							needPower(request, response);
							return false;
						}
						//再遍历list跟用户访问的地址比较
						boolean b = true;
						for(BookPrivilegeVo p : privilegelist){
							if(p!=null){
								if(visiturl.equals(p.getPrivilegeurl())){
									//如果匹配，则该用户拥有访问此资源的权限，则放行
									return true;
								}else{
									b = false;
								}
							}
						}
						if(!b){
							needPower(request, response);
							return false;
						}
					}
				}
			}
			
	    }
		return true;
		
	}

	
	/**
	 * @category 需要登录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void needLogin(HttpServletRequest request, HttpServletResponse response,String path) throws IOException
	{
		String requestURI = request.getRequestURI();
		log.info("需要登录");
		if (request.getHeader("x-requested-with") == null) {
			// 传统提交
			returnHtml(response,path+"?lastUrl="+requestURI);
		} else {
			// 异步提交
			returnJson(response,"此操作需要登录后才能执行，请先登录。");
		}
	}
	
	/**
	 * @category 权限不够
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void needPower(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if (request.getHeader("x-requested-with") == null) {
			// 传统返回
			returnHtml(response,"/noPermissionsPage.jsp");
		} else {
			// 异步返回
			returnJson(response,"你的操作权限不足，请联系管理员。");
		}
	}
	

	/**
	 * @category 同步返回 HTML
	 * @param response
	 * @throws IOException
	 */
	private void returnHtml(HttpServletResponse response,String uri) throws IOException
	{
		response.getWriter().write("<script>window.top.location='" + SysConfigUtil.getSysConfig().getSysBasePath() + uri + "'</script>");

	}
	
	
	/**
	 * @category 异步返回 json
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void returnJson(HttpServletResponse response,String message) throws IOException
	{
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Content-type", "text/x-json");

		JSONObject jsonObj = new JSONObject();
		ReturnMessage rm = new ReturnMessage();
		rm.setMessage(message);
		rm.setResult(false);
		jsonObj = JSONObject.fromObject(rm);

		PrintWriter out = response.getWriter();
		out.println(jsonObj);
		out.flush();
		out.close();
	}
	
}
