package com.multi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.data.domain.Persistable;
import com.alibaba.fastjson.annotation.JSONField;

@Table(name="JPA_CUSTOMERS")
@Entity
//public class Customer {
public class Customer implements Persistable<Object>{

	private Integer id;
	private String lastName;

	private String email;
	private Integer age;
	private Integer addr_id;

	private Date createdTime;
	private Date birth;


	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public Customer(Integer id) {
		super();
		this.id = id;
	}
	public Customer(String lastName, int age) {
		super();
		this.lastName = lastName;
		this.age = age;
	}
	public Customer(int id, String lastName, int age) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.age = age;
	}


//	@TableGenerator(name="ID_GENERATOR",
//			table="jpa_id_generators",
//			pkColumnName="PK_NAME",
//			pkColumnValue="CUSTOMER_ID",
//			valueColumnName="PK_VALUE",
//			allocationSize=100)
//	@GeneratedValue(strategy=GenerationType.TABLE,generator="ID_GENERATOR")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	//工具方法. 不需要映射为数据表的一列. 
	@Transient
	@JSONField(serialize = false)
	public String getInfo(){
		return "lastName: " + lastName + ", email: " + email;
	}

	@Column(name="createdTime")
	@Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
    @Column(name="ADDR_ID")
	public Integer getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(Integer addr_id) {
		this.addr_id = addr_id;
	}
	
	@Override
	@Transient
	public boolean isNew() {
		// TODO Auto-generated method stub
		return true;
	}
    
    
}