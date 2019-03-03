package com.springBoot.fastjson;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.multi.entity.Customer;
import com.springBoot.tool.DateTool;

public class FastjsonTests1 {
	
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
