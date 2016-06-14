package com.ithxh.xhh.exception;

@SuppressWarnings("serial")
public class UpdateFailException extends BaseException{

	public static String MESSAGE = "编辑失败，请稍后再试";

	public UpdateFailException() {
		super();
		
	}

	public UpdateFailException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public UpdateFailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public UpdateFailException(String arg0) {
		super(arg0);
		MESSAGE = arg0;
	}

	public UpdateFailException(Throwable arg0) {
		super(arg0);
		
	}
	
	
}
