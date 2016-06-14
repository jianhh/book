package com.ithxh.xhh.exception;

/**
 * @category 页面不存在 
 * @author 何建辉
 * @date 2015年12月10日 下午2:18:23
 */
@SuppressWarnings("serial")
public class PageNotFoundException extends RuntimeException{
	
	public static String MESSAGE = "该页面不存在";

	public PageNotFoundException() {
		super();
	}

	public PageNotFoundException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public PageNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PageNotFoundException(String arg0) {
		super(arg0);
		MESSAGE = arg0;
	}

	public PageNotFoundException(Throwable arg0) {
		super(arg0);
	}
}
