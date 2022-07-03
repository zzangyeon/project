package com.hello.project.handler.exception;

public class CustomApiException extends RuntimeException{

	//객체를 구분할대 쓰는것(jvm이)
	private static final long serialVersionUID = 1L;
	
	public CustomApiException(String message) {
		super(message);                               
	}

}
