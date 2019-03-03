package com.springBoot.tool;

import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Component
public final class SpringTool implements ApplicationContextAware {

	
    private static ApplicationContext context;

    public SpringTool() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    /**
     * 获取ApplicationContext
     * 
     * @return
     */
    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 根据类名获取bean
     * 
     * @param beanType
     * @return
     */
    public static <T> T getBean(Class<T> beanType) {
        T t = getContext().getBean(beanType);
        return t;
    }

    /**
     * 根据beanId获取bean
     * 
     * @param beanId
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId) {
    	beanId = StringUtils.uncapitalize(beanId);
        T t = (T) getContext().getBean(beanId);
        return t;
    }
}