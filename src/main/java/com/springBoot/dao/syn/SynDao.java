package com.springBoot.dao.syn;

import java.util.Map;

public interface SynDao {

	@SuppressWarnings("rawtypes")
	public Map<String, Object> getAll(Class clazz);
	
	public void save(Map<String, Object> map);
}
