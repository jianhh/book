package com.ithxh.xhh.exception;

@SuppressWarnings("serial")
public class DeleteFailException extends BaseException{

	public static String MESSAGE = "删除失败，请稍后再试";

	public DeleteFailException() {
		super();
		
	}

	public DeleteFailException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	public DeleteFailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public DeleteFailException(String arg0) {
		super(arg0);
		MESSAGE = arg0;
	}

	public DeleteFailException(Throwable arg0) {
		super(arg0);
		
	}
	
}
