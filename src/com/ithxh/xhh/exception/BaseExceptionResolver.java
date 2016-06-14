package com.ithxh.xhh.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ithxh.baseCore.model.ReturnMessage;

public class BaseExceptionResolver implements HandlerExceptionResolver{// extends SimpleMappingExceptionResolver{

	private Logger logger = null;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ex.printStackTrace();
		logger = LoggerFactory.getLogger(handler.getClass());
		logger.error(ex.getLocalizedMessage());
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                .getHeader("X-Requested-With")!= null && request  
                .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
            // 如果不是异步请求  
            if (ex instanceof AddFailException) {
    			model.put("error", AddFailException.MESSAGE);
 			}else if (ex instanceof FileUploadFailException) {
 				model.put("error", FileUploadFailException.MESSAGE);
 				
 			}else if (ex instanceof DeleteFailException) {
 				model.put("error", DeleteFailException.MESSAGE);
 				
 			}else if (ex instanceof UpdateFailException) {
 				model.put("error", UpdateFailException.MESSAGE);
 				
 			}else if (ex instanceof SystemBusyException) {
 				model.put("error", SystemBusyException.MESSAGE);
 				
 			}else if (ex instanceof OperateFailException) {
 				model.put("error", OperateFailException.MESSAGE);
 				
 			}else if (ex instanceof RuntimeException) {
 				model.put("error", SystemBusyException.MESSAGE);
 				
 			}else {
 				model.put("error", AddFailException.MESSAGE);
			}
            return new ModelAndView("error/error", model);
            
        } else {// JSON格式返回  
        	try {
        		if (ex instanceof AddFailException) {
        			returnJson(response,AddFailException.MESSAGE);
                 	
     			}else if (ex instanceof FileUploadFailException) {
     				returnJson(response,FileUploadFailException.MESSAGE);
     				
     			}else if (ex instanceof DeleteFailException) {
     				returnJson(response,DeleteFailException.MESSAGE);
     				
     			}else if (ex instanceof UpdateFailException) {
     				returnJson(response,UpdateFailException.MESSAGE);
     				
     			}else if (ex instanceof SystemBusyException) {
     				returnJson(response,SystemBusyException.MESSAGE);
     				
     			}else if (ex instanceof OperateFailException) {
     				returnJson(response,OperateFailException.MESSAGE);
     				
     			}else if (ex instanceof RuntimeException) {
     				returnJson(response,SystemBusyException.MESSAGE);
     				
     			}else {
     				returnJson(response,SystemBusyException.MESSAGE);
				}
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage());
			}
	    }
		return null;
	}
	
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
	
	/*@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		String viewName = determineViewName(ex, request);
        if (viewName != null) {// JSP格式返回  
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
                    .getHeader("X-Requested-With")!= null && request  
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
                // 如果不是异步请求  
                Integer statusCode = determineStatusCode(request, viewName);  
                if (statusCode != null) {  
                    applyStatusCodeIfPossible(request, response, statusCode);  
                }  
                return getModelAndView(viewName, ex, request);  
            } else {// JSON格式返回  
                try {  
                    PrintWriter writer = response.getWriter();  
                    writer.write(ex.getMessage());  
                    writer.flush();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                return null;  
  
            }  
        } else {  
            return null;  
        }  
		
	}*/
}
