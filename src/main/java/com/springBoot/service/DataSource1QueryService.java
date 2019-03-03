package com.springBoot.service;

import java.util.Map;

public interface DataSource1QueryService {

	public Map<String, Object> getAll(Class clazz);
	
	public Map<String, Object> getAllId(Class clazz);
}
