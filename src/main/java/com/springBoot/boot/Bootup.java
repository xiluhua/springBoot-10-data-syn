package com.springBoot.boot;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;

import com.springBoot.tool.IOTool;
import com.springBoot.tool.TemplateToolV1218;

public class Bootup {
	
	static boolean isFirst										= true;
	public final static String utf8 				 			= "utf-8";
	public final static String APPLICATION_CONTEXT_XML 			= "applicationContext.xml";
	public final static String APPLICATION_CONTEXT_XML_ORIGIN 	= "applicationContextOrigin.xml";
	private final static String POINTCUT_SPACE 					= "<!--POINTCUT SPACE-->";     
	private final static String ADVISER_SPACE  					= "<!--ADVISER SPACE-->"; 
	private final static String POINTCUT  = "<aop:pointcut id=\"$!{advice-ref}PointCut$!{index}\"  expression=\"execution(* $!{pkg}.*(..))\" />"; 
	private final static String ADVISER   = "<aop:advisor  pointcut-ref=\"$!{advice-ref}PointCut${index}\" advice-ref=\"$!{advice-ref}\" order=\"$!{order}\" />"; 
	
	public boolean createAopConfigByAnnotation(List<AopConfig> aopConfigs, String ... pkgs) {
		Set<String> candidateBeans				= new HashSet<>();      
		Map<String, AopBean> aopBeans			= new HashMap<>();
		GenericApplicationContext context 		= new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner  = new ClassPathBeanDefinitionScanner(context);
		boolean flag = true;
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------- CREATE AOP CONFIG BEGIN! ---------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------");
		
		try {
			for (int i = 0; i < pkgs.length; i++) {
				int beanCount = scanner.scan(pkgs[i]);
				System.out.println("beanCount: "+beanCount);
				String[] beans = context.getBeanDefinitionNames();
				candidateBeans.addAll(Arrays.asList(beans));
			}
			
			for (AopConfig aopConfig : aopConfigs) {
				aopBeans.putAll(this.loadBeanClassesAnnoted(context, candidateBeans, aopConfig));
			}
			
			System.out.println("anno beanCount: "+aopBeans.size());
			if (aopBeans.size() == 0) {
				return true;
			}
			
			StringBuilder builder1 = this.buildPointcut(aopBeans);
			StringBuilder builder2 = this.buildAdvisor(aopBeans);
			
			System.out.println(builder1.toString());
			System.out.println(builder2.toString());
			
			// read
			URL url = Bootup.class.getClassLoader().getResource(APPLICATION_CONTEXT_XML_ORIGIN);
			System.out.println(APPLICATION_CONTEXT_XML_ORIGIN+" path: "+url.getPath());
			String content = IOTool.readFile(url.getPath(), utf8);
			
			// rewrite
			URL url2 = Bootup.class.getClassLoader().getResource(APPLICATION_CONTEXT_XML);
			System.out.println(APPLICATION_CONTEXT_XML+" path: "+url2.getPath());
			content = content.replaceAll(POINTCUT_SPACE, builder1.toString());
			content = content.replaceAll(ADVISER_SPACE,  builder2.toString());
			IOTool.writeIntoTxt(url2.getPath(), content, false, utf8);
			content = IOTool.readFile(url2.getPath(), utf8);
			
			System.out.println(content);
			System.out.println("*********************************************************************************************");
			System.out.println("******************************** CREATE AOP CONFIG SUCCESS! *********************************");
			System.out.println("*********************************************************************************************");
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			context = null;
			scanner = null;
		}
		return flag;
	}

	private StringBuilder buildPointcut(Map<String, AopBean> aopBeans) throws ClassNotFoundException {
		StringBuilder builder = new StringBuilder();
		
		int i = 0;
		for (Map.Entry<String, AopBean> entry : aopBeans.entrySet()) {
			AopBean aopBean = entry.getValue();
			String pointcut = this.fill(POINTCUT, aopBean, (i+1));
			builder.append(pointcut);
			builder.append(System.getProperty("line.separator"));
			i++;
		}
		
		return builder;
	}

	private StringBuilder buildAdvisor(Map<String, AopBean> aopBeans) throws ClassNotFoundException {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		for (Map.Entry<String, AopBean> entry : aopBeans.entrySet()) {
			AopBean aopBean = entry.getValue();
			String advisor = this.fill(ADVISER, aopBean, (i+1));
			builder.append(advisor);
			builder.append(System.getProperty("line.separator"));
			i++;
		}
		return builder;
	}

	private Map<String, AopBean> loadBeanClassesAnnoted(GenericApplicationContext context, Set<String> beans, AopConfig aopConfig) throws ClassNotFoundException {
		Map<String, AopBean> map1 = this.loadBeanClassesAnnotedClass(context, beans, aopConfig);
		Map<String, AopBean> map2 = this.loadBeanClassesAnnotedMethod(context, beans, aopConfig, map1);
		map1.putAll(map2);
		return map1;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, AopBean> loadBeanClassesAnnotedClass(GenericApplicationContext context, Set<String> beans, AopConfig aopConfig) throws ClassNotFoundException {
		Map<String, AopBean> map  = new HashMap<>();
		Class anno = aopConfig.getAnno();
		for (String bean : beans) {
			String clazz = context.getBeanDefinition(bean).getBeanClassName();
			if (Class.forName(clazz).isAnnotationPresent(anno)) 
			{
				AopBean aopBean = new AopBean();
				aopBean.setAopConfig(aopConfig);
				aopBean.setAnno(anno);				
				aopBean.setBeanDefinition(context.getBeanDefinition(bean));
				aopBean.setElementType(ElementType.TYPE);
				String key = bean+":"+anno.getSimpleName()+":"+aopBean.getElementType();
				map.put(key, aopBean);
			}
		}
		return map;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, AopBean> loadBeanClassesAnnotedMethod(GenericApplicationContext context, Set<String> beans, AopConfig aopConfig, Map<String, AopBean> classMap) throws ClassNotFoundException {
		Map<String, AopBean> map  = new HashMap<>();
		Class anno = aopConfig.getAnno();
		for (String bean : beans) {
			String clazz = context.getBeanDefinition(bean).getBeanClassName();
			Method[] methods = Class.forName(clazz).getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(anno)) 
				{
					AopBean aopBean = new AopBean();
					aopBean.setAopConfig(aopConfig);
					aopBean.setAnno(anno);				
					aopBean.setBeanDefinition(context.getBeanDefinition(bean));
					aopBean.setElementType(ElementType.METHOD);
					aopBean.setMethod(method);
					// 已经注解 class 则忽略 method
					String key1 = bean+":"+anno.getSimpleName()+":"+ElementType.TYPE;
					String key2 = bean+":"+anno.getSimpleName()+":"+aopBean.getElementType()+":"+method.getName();
					if ( classMap != null && classMap.get(key1) != null) {
						break;
					}
					map.put(key2, aopBean);
				}
			}
		}
		return map;
	}
	
	private String fill(String template, AopBean aopBean, int index) {
		String pkg = null;
		if (aopBean.getMethod() != null) {
			pkg = aopBean.getBeanDefinition().getBeanClassName()+"."+aopBean.getMethod().getName();
		} else {
			pkg = aopBean.getBeanDefinition().getBeanClassName();
		}
		
		VelocityContext context = new VelocityContext();
		context.put("advice-ref", aopBean.getAopConfig().getAdviceRef());
		context.put("index", index);
		context.put("pkg", pkg);
		context.put("order", aopBean.getAopConfig().getOrder());
		String result = TemplateToolV1218.fill(context, template);
		
		if (aopBean.getMethod() != null) {
			result = result.replace(".*", "");
		}
		
		if (index != 1 ) {
			result = "\t\t"+result;
		}
		return result;
	}
}
