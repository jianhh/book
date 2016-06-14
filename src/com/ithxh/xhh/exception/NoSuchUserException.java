package com.ithxh.xhh.exception;

@SuppressWarnings("serial")
public class NoSuchUserException extends BaseException{

	public static String MESSAGE = "该用户不存在";

	public NoSuchUserException() {
		super();
		
	}

	public NoSuchUserException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public NoSuchUserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public NoSuchUserException(String arg0) {
		super(arg0);
		MESSAGE = arg0;
	}

	public NoSuchUserException(Throwable arg0) {
		super(arg0);
		
	}
	
	
}
