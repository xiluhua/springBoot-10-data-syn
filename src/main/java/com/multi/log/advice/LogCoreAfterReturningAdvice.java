package com.multi.log.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import com.multi.log.LogCoreAfterReturning;

@Component
public class LogCoreAfterReturningAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("isAnnotationPresent(LogCoreAfterReturningAdvice.class): "+method.isAnnotationPresent(LogCoreAfterReturning.class));
		System.out.println(LogCoreAfterReturningAdvice.class+" method: "+method.getName());
	}

}
