package com.ispan.wu.dao.result;

public interface Result {
	String getMsg();
	void setMsg(String msg);
	
	boolean getIsSuccess();
	void setIsSuccess(boolean isSuccess);
	
	Exception getException();
	void setException(Exception exception);
}
