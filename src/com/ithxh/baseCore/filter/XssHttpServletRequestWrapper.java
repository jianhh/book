package com.ithxh.baseCore.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {

		super(servletRequest);
	}
	
	@Override
	public String[] getParameterValues(String parameter) {

		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}

		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			// System.out.println("---------------2  "+values[i]);
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}
	 /** 
	    * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/> 
	    * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/> 
	    * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
	    */  
	@Override
	public String getParameter(String parameter) {

		String value = super.getParameter(parameter);
		if (value == null) {                           //  www.jointpforce.com   
			return null;
		}
		// System.out.println("---------------"+value);
		return cleanXSS(value);
	}
	/** 
	    * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/> 
	    * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> 
	    * getHeaderNames 也可能需要覆盖 
	    */  
	@Override
	public String getHeader(String name) {

		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);
	}

	private String cleanXSS(String s) {
//		if (!value.trim().equals("") && !value.trim().startsWith("ck-$#pass")) {
//			value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
//			value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
//			value = value.replaceAll("'", "&#39;");
//			value = value.replaceAll("eval\\((.*)\\)", "");
//			value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
//			value = value.replaceAll("script", "");
//		}
		 if (s == null || "".equals(s)) {  
	            return s;  
	        }  
	        StringBuilder sb = new StringBuilder(s.length() + 16);  
	        for (int i = 0; i < s.length(); i++) {  
	            char c = s.charAt(i);  
	            switch (c) {  
	            case '>':   
	                sb.append('＞');//全角大于号  
	                break;  
	            case '<':  
	                sb.append('＜');//全角小于号  
	                break;  
	            case '\'':  
	                sb.append('‘');//全角单引号  
	                break;  
	            case '\"':  
	                sb.append('“');//全角双引号  
	                break;  
	            case '&':  
	                sb.append('＆');//全角  
	                break;  
	            case '\\':  
	                sb.append('＼');//全角斜线  
	                break;  
	            case '#':  
	                sb.append('＃');//全角井号  
	                break;  
	            default:  
	                sb.append(c);  
	                break;  
	            }  
	        }  
	        return sb.toString();  
		//return value;
	}
}