package com.springBoot.dao.syn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.multi.entity.Customer;
import com.multi.entity.Order;
import com.springBoot.dao.CustomerRepDao;
import com.springBoot.tool.DateTool;

@Repository
public class CustomerSynDao implements SynDao{

	@Resource
	CustomerRepDao customerRepDao;

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getAll(Class clazz) {
		Iterable<Customer> it = customerRepDao.findAll();
		List<Customer> list = new ArrayList<>();
		for (Customer obj : it) {
			list.add(obj);
		}
		
		Map<String, Object> map = SynTool.put(clazz, JSON.toJSONStringWithDateFormat(list, DateTool.DATE_TIME_MASK));
		return map;
	}

	@Override
	public void save(Map<String, Object> map) {
		List<Customer> list2 =  JSON.parseObject((String)map.get("list"), new TypeReference<ArrayList<Customer>>() {});
		for (Customer obj : list2) {
			customerRepDao.save(obj);
			System.out.println(JSON.toJSONStringWithDateFormat(obj, DateTool.DATE_TIME_MASK));
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getAllId(Class clazz) {
		List<Integer> list = customerRepDao.getAllId();
		Map<String, Object> map = SynTool.put(clazz, JSON.toJSONStringWithDateFormat(list, DateTool.DATE_TIME_MASK));
		return map;
	}
	
	@Override
	public boolean isExist(Integer id) {
		return customerRepDao.existsById(id);
	}

	@Override
	public String findById(Integer id) {
		Customer obj = customerRepDao.findById(id).get();
		String json  = JSON.toJSONStringWithDateFormat(obj, DateTool.DATE_TIME_MASK);
		return json;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> saveAll(Map<String, Object> map){
		List<Customer> objs = new ArrayList<>();
		
		Class clazz = (Class)map.get("class");
		List<String> list = (List<String>)map.get("list");
		for (String json : list) {
			Customer obj = JSON.parseObject(json, new TypeReference<Customer>() {});
			objs.add(obj);
		}
		Iterable<Customer> it = customerRepDao.saveAll(objs);
		
		objs.clear();
		for (Customer obj : it) {
			objs.add(obj);
		}
		map = SynTool.put(clazz, JSON.toJSONStringWithDateFormat(objs, DateTool.DATE_TIME_MASK));
		return map;
	}
}
