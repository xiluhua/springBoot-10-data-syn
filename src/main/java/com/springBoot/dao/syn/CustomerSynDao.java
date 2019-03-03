package com.springBoot.dao.syn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.multi.entity.Customer;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		List<Customer> list = new ArrayList<>();
		Customer c1 = new Customer();
		c1.setAddr_id(111);
		c1.setAge(1);
		c1.setEmail("fsfsf");
		Customer c2 = new Customer();
		c2.setAddr_id(222);
		c2.setAge(2);
		c2.setEmail("ldsjfls");
		list.add(c2);
		list.add(c1);
		String json = JSON.toJSONStringWithDateFormat(list, DateTool.DATE_TIME_MASK);
		System.out.println("json: "+json);
		
		List<Customer> list2 =  JSON.parseObject(json, new TypeReference<ArrayList<Customer>>() {});
		for (Customer customer : list2) {
			System.out.println(JSON.toJSONStringWithDateFormat(customer, DateTool.DATE_TIME_MASK));
		}
	}
}
