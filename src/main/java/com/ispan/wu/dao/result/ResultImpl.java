package com.ispan.wu.dao.result;

public class ResultImpl implements Result {
	private String msg="";
	private boolean isSuccess=false;
	private Exception exception=null;

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public void setMsg(String msg) {
		this.msg=msg;
	}

	@Override
	public boolean getIsSuccess() {
		return isSuccess;
	}

	@Override
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess=isSuccess;
	}

	@Override
	public Exception getException() {
		return exception;
	}

	@Override
	public void setException(Exception exception) {
		this.exception=exception;
	}



}
