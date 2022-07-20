package com.hello.project.handler;

import com.hello.project.dto.CMRespDto;
import com.hello.project.handler.exception.CustomApiException;
import com.hello.project.handler.exception.CustomException;
import com.hello.project.handler.exception.CustomValidationApiException;
import com.hello.project.handler.exception.CustomValidationException;
import com.hello.project.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

	 @ExceptionHandler(CustomValidationException.class)
	 public String vallidationException(CustomValidationException e) {

		 if(e.getErrorMap() == null) {
			 return Script.back(e.getMessage());
		 }else {
			 return Script.back(e.getErrorMap().toString());
		 }
     }

	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<CMRespDto<?>> vallidationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
		//http상태코드와 보낼메세지를 함께 보낼 수 있다. Responseentity
	}

	@ExceptionHandler(CustomException.class)
	public String Exception(CustomException e) {
		return Script.back(e.getMessage());
	}
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<CMRespDto<?>> ApiException(CustomApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
	}



}
