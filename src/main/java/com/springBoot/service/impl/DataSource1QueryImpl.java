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
	
	
}
