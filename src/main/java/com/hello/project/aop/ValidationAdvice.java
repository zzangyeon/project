package com.hello.project.aop;

import com.hello.project.handler.exception.CustomValidationApiException;
import com.hello.project.handler.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 logging
 validation check
 */

@Component
@Aspect
@Slf4j
public class ValidationAdvice { //aop 공통적인 전처리를 함!

    @Around("execution(* com.hello.project.controller.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().getName();
        String fullClass = joinPoint.getSignature().getDeclaringTypeName();
        String oneClass = fullClass.substring(fullClass.lastIndexOf(".")+1);
        log.info("[ {} / {} ]",oneClass,method);

        Object[] args = joinPoint.getArgs();
        for(Object arg:args) {
            if(arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult)arg;//다운 캐스팅
                if(bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<String, String>();
                    for(FieldError error:bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(),error.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성검사 실패함",errorMap);
                }
            }
        }
        return joinPoint.proceed();
        //proceedingJoinPoint - profile 함수의 모든 곳에 접근할 수 있는 변수
        //controller 내의 함수보다 먼저 실행됨

    }

    @Around("execution(* com.hello.project.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().getName();
        String fullClass = joinPoint.getSignature().getDeclaringTypeName();
        log.info("====[ {} / {} ]====",fullClass,method);

        Object[] args = joinPoint.getArgs();
        for(Object arg:args) {
            if(arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult)arg;
                if(bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<String, String>();
                    for(FieldError error:bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(),error.getDefaultMessage());
                    }
                    throw new CustomValidationApiException("유효성검사 실패함",errorMap);
                }
            }
        }
        return joinPoint.proceed();
    }



}
