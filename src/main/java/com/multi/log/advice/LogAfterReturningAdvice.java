package com.multi.log.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import com.multi.log.LogAfterReturning;

@Component
public class LogAfterReturningAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("isAnnotationPresent(LogAfterReturning.class): "+method.isAnnotationPresent(LogAfterReturning.class));
		System.out.println(LogAfterReturningAdvice.class+" method: "+method.getName());
	}


}
