package com.springBoot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.multi.entity.Customer;

// 1. 第一种方式	
//@RepositoryDefinition(domainClass=Customer.class,idClass=Integer.class)
//public interface CustomerRepDao {
	
// 2. 第二种方式	
//public interface CustomerRepDao extends Repository<Customer, Integer>{

// 3. 第三种方式	
//public interface CustomerRepDao extends CrudRepository<Customer, Integer>{

// 4. 第四种方式	
public interface CustomerRepDao extends PagingAndSortingRepository<Customer, Integer>{

	public Customer getByLastName(String lastName);
	
	public List<Customer> getByLastNameStartingWithAndIdLessThan(String lastName, Integer id);
	
	public List<Customer> getByIdGreaterThan(Integer id);
	
	public List<Customer> getByLastNameInOrIdLessThan(List<String> names, Integer id);
	
	@Query(value="SELECT c FROM Customer c WHERE c.id = (SELECT MAX(c2.id) FROM Customer c2)")
	public Customer getMaxIdPerson5();
	
	@Query(value="SELECT COUNT(c) FROM Customer c")
	public int count6();
	
	// 使用占位符
	@Query(value="SELECT c FROM Customer c WHERE c.lastName = ?1 and c.email = ?2")
	public Customer query7(String lastName, String email);

	// 使用命名参数
	@Query(value="SELECT c FROM Customer c WHERE c.lastName = :lastName and c.email = :email")
	public Customer query8(@Param("email") String email, @Param("lastName") String lastName);
	
	// like
	@Query(value="SELECT c FROM Customer c WHERE c.lastName LIKE ?1 and c.email LIKE ?2")
	public List<Customer> query9(String lastName, String email);
	
	// like
	@Query(value="SELECT c FROM Customer c WHERE c.lastName LIKE ?1% and c.email LIKE ?2%")
	public List<Customer> query10(String lastName, String email);
	
	// nativeQuery 1
	@Query(value="SELECT COUNT(*) FROM JPA_CUSTOMERS", nativeQuery=true)
	public int count11();
	
	@Modifying
	@Query(value="UPDATE Customer c set c.email = :email where c.id = :id")
	public int update13(@Param("email") String email, @Param("id") int id);
	
	
	
	/**
	 * 本例
	 * @author xiluhua
	 * @since 20190303
	 * @return
	 */
	@Query(value="SELECT c.id FROM Customer c order by c.id")
	public List<Integer> getAllId(); 

}