package com.ithxh.xhh.exception;

@SuppressWarnings("serial")
public class AddFailException extends BaseException{

	public static String MESSAGE = "添加失败，请稍后再试";

	public AddFailException() {
		super();
		
	}

	public AddFailException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public AddFailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AddFailException(String arg0) {
		super(arg0);
		MESSAGE = arg0;
	}

	public AddFailException(Throwable arg0) {
		super(arg0);
		
	}
	
	
}
