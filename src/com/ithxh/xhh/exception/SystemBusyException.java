package com.ithxh.xhh.exception;

@SuppressWarnings("serial")
public class SystemBusyException extends BaseException{

	public static String MESSAGE = "系统繁忙，请稍后再试";

	public SystemBusyException() {
		super();
		
	}

	public SystemBusyException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public SystemBusyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public SystemBusyException(String arg0) {
		super(arg0);
		MESSAGE = arg0;
	}

	public SystemBusyException(Throwable arg0) {
		super(arg0);
		
	}
	
}
