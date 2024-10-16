package com.ispan.wu;

public enum ConstEnum {
	WORK_STRING("work"), 
	QUERY_ALL_STRING("queryAll"), 
	QUERY_BY_ID_STRING("queryById"),
	QUERY_OBSCURE_BY_NAME_STRING("queryObscureByName"),
	DELETE_BY_ID_STRING("deleteByID"),
	DELETE_ALL_STRING("deleteAll"),
	INSERT_STRING("insert"),
	INSERT_FROM_DEFAULT_STRING("insertFromDefault"),
	INSERT_FROM_FILE_STRING("insertFromFile");
	
	private String content;
	private ConstEnum(String content) {
		this.content = content;
	}
}
