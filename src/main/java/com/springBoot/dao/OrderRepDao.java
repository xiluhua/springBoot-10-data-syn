package com.springBoot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.multi.entity.Order;

public interface OrderRepDao extends PagingAndSortingRepository<Order, Integer>{
	
	@Query(value="SELECT c.id FROM Order c order by c.id")
	public List<Integer> getAllId(); 

}