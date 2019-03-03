package com.springBoot.dao.syn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.multi.entity.Order;
import com.springBoot.dao.OrderRepDao;
import com.springBoot.tool.DateTool;

@Repository
public class OrderSynDao implements SynDao{

	@Resource
	OrderRepDao orderRepDao;

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getAll(Class clazz) {
		Iterable<Order> it = orderRepDao.findAll();
		List<Order> list = new ArrayList<>();
		for (Order obj : it) {
			list.add(obj);
		}
		
		Map<String, Object> map = SynTool.put(clazz, JSON.toJSONStringWithDateFormat(list, DateTool.DATE_TIME_MASK));
		return map;
	}

	@Override
	public void save(Map<String, Object> map) {
		List<Order> list2 =  JSON.parseObject((String)map.get("list"), new TypeReference<ArrayList<Order>>() {});
		for (Order obj : list2) {
			orderRepDao.save(obj);
			System.out.println(JSON.toJSONStringWithDateFormat(obj, DateTool.DATE_TIME_MASK));
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getAllId(Class clazz) {
		List<Integer> list = orderRepDao.getAllId();
		Map<String, Object> map = SynTool.put(clazz, JSON.toJSONStringWithDateFormat(list, DateTool.DATE_TIME_MASK));
		return map;
	}

	@Override
	public boolean isExist(Integer id) {
		return orderRepDao.existsById(id);
	}
	
	@Override
	public String findById(Integer id) {
		Order obj = orderRepDao.findById(id).get();
		String json  = JSON.toJSONStringWithDateFormat(obj, DateTool.DATE_TIME_MASK);
		return json;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> saveAll(Map<String, Object> map){
		List<Order> objs = new ArrayList<>();
		
		Class clazz = (Class)map.get("class");
		List<String> list = (List<String>)map.get("list");
		for (String json : list) {
			Order obj = JSON.parseObject(json, new TypeReference<Order>() {});
			objs.add(obj);
		}
		Iterable<Order> it = orderRepDao.saveAll(objs);
		
		objs.clear();
		for (Order obj : it) {
			objs.add(obj);
		}
		map = SynTool.put(clazz, JSON.toJSONStringWithDateFormat(objs, DateTool.DATE_TIME_MASK));
		return map;
	}
	
}
