package com.springBoot.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.multi.dataSource.MultiDataSource;
import com.springBoot.dao.syn.SynDao;
import com.springBoot.service.DataSource1QueryService;
import com.springBoot.tool.SpringTool;

@Service
public class DataSource1QueryImpl implements DataSource1QueryService{

	@SuppressWarnings("rawtypes")
	@MultiDataSource(name=MultiDataSource.dataSource1)
	@Override
	public Map<String, Object> getAll(Class clazz) {
		SynDao synDao = SpringTool.getBean(clazz.getSimpleName()+"SynDao");
		return synDao.getAll(clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@MultiDataSource(name=MultiDataSource.dataSource1)
	@Override
	public Map<String, Object> getAllId(Class clazz) {
		SynDao synDao = SpringTool.getBean(clazz.getSimpleName()+"SynDao");
		return synDao.getAllId(clazz);
	}
	
	@SuppressWarnings("rawtypes")
	@MultiDataSource(name=MultiDataSource.dataSource1)
	public String findById(Map<String, Object> map) {
		Class clazz = (Class)map.get("class");
		Integer id     = (Integer)map.get("id");
		SynDao synDao = SpringTool.getBean(clazz.getSimpleName()+"SynDao");
		String json = synDao.findById(id);
		return json;
	}
}
