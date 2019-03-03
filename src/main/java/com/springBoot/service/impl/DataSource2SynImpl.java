package com.springBoot.service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.multi.dataSource.MultiDataSource;
import com.springBoot.dao.syn.SynDao;
import com.springBoot.service.DataSource2SynService;
import com.springBoot.tool.SpringTool;

@Service
@Transactional
public class DataSource2SynImpl implements DataSource2SynService {
         
	@SuppressWarnings("rawtypes")
	@MultiDataSource(name=MultiDataSource.dataSource2)
	public void save(Map<String, Object> map) {
		Class clazz = (Class)map.get("class");
		SynDao synDao = SpringTool.getBean(clazz.getSimpleName()+"SynDao");
		synDao.save(map);
	}

	@SuppressWarnings("rawtypes")
	@MultiDataSource(name=MultiDataSource.dataSource2)
	public boolean isExist(Map<String, Object> map) {
		Class clazz = (Class)map.get("class");
		Integer id     = (Integer)map.get("id");
		SynDao synDao = SpringTool.getBean(clazz.getSimpleName()+"SynDao");
		return synDao.isExist(id);
	}
	
	@SuppressWarnings("rawtypes")
	@MultiDataSource(name=MultiDataSource.dataSource2)
	public Map<String, Object> saveAll(Map<String, Object> map) {
		Class clazz = (Class)map.get("class");
		SynDao synDao = SpringTool.getBean(clazz.getSimpleName()+"SynDao");
		return synDao.saveAll(map);
	}
}
