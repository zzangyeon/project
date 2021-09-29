package com.hello.project.handler;

import com.hello.project.handler.exception.CustomException;
import com.hello.project.util.Script;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//모든 예외 발생시 이 클래스가 예외를 다 낚아챈다
public class ControllerExceptionHandler {
	

//	 @ExceptionHandler(CustomValidationException.class)
//	 public String vallidationException(CustomValidationException e) {

//		 if(e.getErrorMap() == null) {
//			 return Script.back(e.getMessage());
//		 }else {
//			 return Script.back(e.getErrorMap().toString());
//		 }
//     }

	
//	@ExceptionHandler(CustomValidationApiException.class)
//	public ResponseEntity<CMRespDto<?>> vallidationApiException(RuntimeException e) {
//		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
//		//http상태코드와 보낼메세지를 함게 보낼 수 있다. Responseentity
//	}
	
//	@ExceptionHandler(CustomApiException.class)
//	public ResponseEntity<CMRespDto<?>> ApiException(CustomApiException e) {
//		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
//	}
//
	 @ExceptionHandler(CustomException.class)
	 public String Exception(CustomException e) {
			 return Script.back(e.getMessage());

     }

}
