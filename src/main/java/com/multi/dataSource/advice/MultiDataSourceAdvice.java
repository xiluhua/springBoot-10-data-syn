package com.multi.dataSource.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import com.multi.dataSource.DataSourceContextHolder;
import com.multi.dataSource.MultiDataSource;

@Component
public class MultiDataSourceAdvice implements MethodBeforeAdvice,AfterReturningAdvice
{
	@Override
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		DataSourceContextHolder.clearDataSourceType();
	}

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
	
		if (method.isAnnotationPresent(MultiDataSource.class)) 
		{
			MultiDataSource datasource = method.getAnnotation(MultiDataSource.class);
			System.out.println(method.getDeclaringClass().getName()+"."+method.getName()+"(*,*) --- MultiDataSource: "+datasource.name());
			DataSourceContextHolder.setDataSourceType(datasource.name());
			return;
		}
		else
		{
			DataSourceContextHolder.setDataSourceType(MultiDataSource.dataSource1);
			return;
		}
		
//		int id = (int)args[0];
//		if (id % 2 == 0) {
//			DataSourceContextHolder.setDataSourceType(MultiDataSource.dataSource2);
//		}else {
//			DataSourceContextHolder.setDataSourceType(MultiDataSource.dataSource1);
//		}
//		System.out.println(MultiDataSourceAdvice.class+": "+id+" : "+DataSourceContextHolder.getDataSourceType());
	}
}
