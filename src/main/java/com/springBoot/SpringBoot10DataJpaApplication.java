package com.springBoot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.multi.dataSource.MultiDataSource;
import com.multi.entity.Customer;
import com.multi.entity.Order;
import com.multi.log.LogAfterReturning;
import com.multi.log.LogBefore;
import com.multi.log.LogCoreAfterReturning;
import com.springBoot.boot.AopConfig;
import com.springBoot.boot.Bootup;
import com.springBoot.service.impl.DataSource1QueryImpl;
import com.springBoot.service.impl.DataSource2SynImpl;

@ImportResource(value = {"classpath:"+Bootup.APPLICATION_CONTEXT_XML})
@SpringBootApplication
public class SpringBoot10DataJpaApplication {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		if (args == null || args.length == 0) {
			try {
				AopConfig element1 = new AopConfig(MultiDataSource.class, 1);
				AopConfig element2 = new AopConfig(LogBefore.class, 2);
				AopConfig element3 = new AopConfig(LogAfterReturning.class, 2);
				AopConfig element4 = new AopConfig(LogCoreAfterReturning.class, 3);
				AopConfig[] arr    = new AopConfig[] { element1, element2, element3, element4 };
				String[]  pkgs     = new String[] { "com.multi", "com.springBoot" };

				boolean flag = new Bootup().createAopConfigByAnnotation(Arrays.asList(arr), pkgs);
				if ( !flag ) {
					return;
				}
				args = new String[]{"1"};

			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		ConfigurableApplicationContext ctx 	= SpringApplication.run(SpringBoot10DataJpaApplication.class, args);
		Set<Class> set = new HashSet<>();
		set.add(Customer.class);
		set.add(Order.class);
		
		for (Class class1 : set) {
			DataSource1QueryImpl queryImpl		= ctx.getBean(DataSource1QueryImpl.class);
			Map<String, Object> all 			= queryImpl.getAll(class1);
			DataSource2SynImpl synImpl 			= ctx.getBean(DataSource2SynImpl.class);
			synImpl.save(all);
		}
		
	}
}

