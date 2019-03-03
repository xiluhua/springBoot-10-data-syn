package com.multi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

@Table(name="JPA_ORDERS")
@Entity
public class Order implements Persistable<Object>{

	@Id
	private Integer id;
	@Column
	private String orderName;
	@Column(name="CUSTOMER_ID")
	private Integer customerId;

	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(Integer id) {
		super();
		this.id = id;
	}
	public Order(int id, String orderName, int customerId) {
		super();
		this.id = id;
		this.orderName = orderName;
		this.customerId = customerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	@Override
	@Transient
	public boolean isNew() {
		// TODO Auto-generated method stub
		return true;
	}

    
}
