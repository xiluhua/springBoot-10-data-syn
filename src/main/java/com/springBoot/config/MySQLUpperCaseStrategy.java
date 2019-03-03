package com.springBoot.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * 重写 hibernate 对于命名策略中改表名大写为小写的方法
 */
public class MySQLUpperCaseStrategy extends PhysicalNamingStrategyStandardImpl {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	@Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
 
        String tableName = name.getText().toUpperCase();
        return name.toIdentifier(tableName);
    }
 
}