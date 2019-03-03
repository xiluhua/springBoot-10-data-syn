package com.multi.dataSource;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiDataSource {
	
    String name() default MultiDataSource.dataSource1;
    String order() default "1";
    
    public static String dataSource1 = "dataSource1";
 
    public static String dataSource2 = "dataSource2";
}
