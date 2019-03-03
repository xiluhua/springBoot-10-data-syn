package com.springBoot.dao.syn;

import java.util.Map;

public interface SynDao {

	@SuppressWarnings("rawtypes")
	public Map<String, Object> getAll(Class clazz);
	
	public void save(Map<String, Object> map);

	@SuppressWarnings("rawtypes")
	public Map<String, Object> getAllId(Class clazz);
	
	public boolean isExist(Integer id);
	
	public String findById(Integer id);
	
	public Map<String, Object> saveAll(Map<String, Object> map);
}
