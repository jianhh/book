package com.ithxh.xhh.exception;

@SuppressWarnings("serial")
public class FileUploadFailException extends BaseException{
	
	public static String MESSAGE = "文件上传失败，请重新上传";

	public FileUploadFailException() {
		super();
	}

	public FileUploadFailException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public FileUploadFailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FileUploadFailException(String arg0) {
		super(arg0);
		MESSAGE = arg0;
	}

	public FileUploadFailException(Throwable arg0) {
		super(arg0);
	}
	
}
