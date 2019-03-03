package com.springBoot.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author xiluhua by 20160119
 *
 */
public class LogTool {

	public static Map<Object, String> logLevelMap = new HashMap<Object, String>();
	public static org.slf4j.Logger slfLogger = LoggerFactory.getLogger(LogTool.class);
	public static Logger logger = Logger.getLogger(LogTool.class);
	public static Logger log = Logger.getLogger(LogTool.class);
	

	public static String localIp = null;
	public static boolean isLocal = false;
	public static boolean isUat = false;
	public static boolean isFormal = false;
	
	static{
		localIp = getLocalIp();
		isLocal = !LogTool.localIp.startsWith("10.1") && !LogTool.localIp.startsWith("10.4");
		isUat = LogTool.localIp.startsWith("10.1");
		isFormal = (!LogTool.isLocal && !LogTool.isUat);
	}
	
	static{
		localIp = getLocalIp();
	}
	
	public static void init(Map<Object, String> logLevelMap){
		LogTool.logLevelMap = logLevelMap;
	}
	
//	@SuppressWarnings("unchecked")
//	public static void init(){
//		LogTool.logLevelMap = (Map<Object, String>) JsonTool.toObject(JedisClient2.get(ImsLogLevel.class.getSimpleName()), HashMap.class);
//	}
	//-------------------------------------------------------------------------
	public static <T> void debug(Class<T> clazz, String logStr ,boolean isPrintEqualLine) {
		
		if (isPrint(clazz,1)) {
			Logger log2 = Logger.getLogger(clazz);
			
			if (isPrintEqualLine) {
				if(log2.isDebugEnabled()) { 
					log2.debug("===========================================================================\n"+logStr);
				}
			}else {
				if(log2.isDebugEnabled()) { 
					log2.debug(logStr);
				}
			}
		}
		
	}

	public static <T> void info(Class<T> clazz, String logStr ,boolean isPrintEqualLine) {
		
		if (isPrint(clazz,2)) {
			Logger log2 = Logger.getLogger(clazz);
		
			if (isPrintEqualLine) {
				if(log2.isInfoEnabled()) { 
					log2.info("===========================================================================\n"+logStr);
				}
			}else {
				if(log2.isDebugEnabled()) { 
					log2.info(logStr);
				}
			}
		}
	}
	
	public static <T> void warn(Class<T> clazz, String logStr,boolean isPrintEqualLine) {
		if (isPrint(clazz,3)) {
			Logger log2 = Logger.getLogger(clazz);
			
			if (isPrintEqualLine) {
				log2.warn("===========================================================================\n"+logStr);
			}else {
				log2.warn(logStr);
			}
		}
	}
	
	public static <T> void error(Class<T> clazz, String logStr ,boolean isPrintEqualLine) {
		
		Logger log2 = Logger.getLogger(clazz);
		if (isPrintEqualLine) {
			log2.error("===========================================================================\n"+logStr);
		}else {
			log2.error(logStr);
		}
	}

	//-------------------------------------------------------------------------
	public static <T> void debug(Class<T> clazz, String logStr) {
		if (isPrint(clazz,1)) {
			Logger log2 = Logger.getLogger(clazz);
			log2.debug(logStr);
		}
	}

	public static <T> void info(Class<T> clazz, String logStr) {
		if (isPrint(clazz,2)) {
			Logger log2 = Logger.getLogger(clazz);
			log2.info(logStr);
		}
	}
	
	public static <T> void warn(Class<T> clazz, String logStr) {
		
		if (isPrint(clazz,3)) {
			Logger log2 = Logger.getLogger(clazz);
			log2.warn(logStr);
		}
	}
	
	public static <T> void error(Class<T> clazz, String logStr) {
		Logger log2 = Logger.getLogger(clazz);
		log2.error(logStr);
	}
	//-------------------------------------------------------------------------

	public static <T> void debug(Class<T> clazz, String format, Object arg) {
		if (isPrint(clazz,1)) {
			org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
			slfLogger2.debug(format, arg);
		}
	}

	public static <T> void info(Class<T> clazz, String format, Object arg) {
		if (isPrint(clazz,2)) {
			org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
			slfLogger2.info(format, arg);
		}
	}
	
	public static <T> void warn(Class<T> clazz, String format, Object arg) {
		if (isPrint(clazz,3)) {
			org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
			slfLogger2.warn(format, arg);
		}
	}
	
	public static <T> void error(Class<T> clazz, String format, Object arg2) {
		org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
		slfLogger2.error(format,arg2);
	}
	//-------------------------------------------------------------------------
	
	public static <T> void debug(Class<T> clazz, String format, Object arg1, Object arg2) {
		if (isPrint(clazz,1)) {
			org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
			slfLogger2.debug(format, arg1,arg2);
		}
	}

	public static <T> void info(Class<T> clazz, String format,  Object arg1, Object arg2) {
		
		if (isPrint(clazz,2)) {
			org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
			slfLogger2.info(format, arg1,arg2);
		}
	}
	
	public static <T> void warn(Class<T> clazz, String format,  Object arg1, Object arg2) {
		
		if (isPrint(clazz,3)) {
			org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
			slfLogger2.warn(format, arg1,arg2);
		}
	}
	
	public static <T> void error(Class<T> clazz, String format,  Object arg1, Object arg2) {
		org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
		slfLogger2.error(format, arg1,arg2);
	}
	//-------------------------------------------------------------------------
	
	public static <T> void debug(Class<T> clazz, String format, Object[] argArray) {
		
		if (isPrint(clazz,1)) {
			org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
			slfLogger2.debug(format, argArray);
		}
	}

	public static <T> void info(Class<T> clazz, String format,  Object[] argArray) {
		
		if (isPrint(clazz,2)) {
			org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
		
			slfLogger2.info(format, argArray);
		}
	}
	
	public static <T> void warn(Class<T> clazz, String format,  Object[] argArray) {
		
		if (isPrint(clazz,3)) {
			org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
		
			slfLogger2.warn(format, argArray);
		}
	}
	
	public static <T> void error(Class<T> clazz, String format,  Object[] argArray) {
		org.slf4j.Logger slfLogger2 = LoggerFactory.getLogger(clazz);
		slfLogger2.error(format, argArray);
	}
	//-------------------------------------------------------------------------
	
	public static <T> void error(Class<T> clazz, Exception e) {
		Logger log2 = Logger.getLogger(clazz);
		log2.error(getExceptionStr(e));
	}
	
	//-------------------------------------------------------------------------
	
	public static String getExceptionStr(Exception e) {
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = null;
		StringBuffer buffer = null;
		try {
			writer = new PrintWriter(stringWriter);
			e.printStackTrace(writer);
			buffer = stringWriter.getBuffer();
			stringWriter.close();
			writer.close();
		} catch (IOException e1) {
			LogTool.error(LogTool.class, e1);
		}finally {
			try {
				stringWriter.close();
				writer.close();
			} catch (IOException e1) {
				LogTool.error(LogTool.class, e1);
			}
		}
		
		return buffer.toString();
	}
	


	/**
	 * 获取本机IP地址
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getLocalIp(){
		String localIp = "";
		Enumeration allNetInterfaces = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//			System.out.println(netInterface.getName());
			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				
				if (ip != null && ip instanceof Inet4Address) {

//					System.out.println("local IP address = " + ip.getHostAddress());
					localIp = ip.getHostAddress();
					if (!localIp.equals("127.0.0.1")) {
						return localIp;
					}
					
				}
			}
		}
		return localIp;
	}
	
	/**
	 * it didn't work in weblogic
	 * @param className
	 * @param level
	 */
	@Deprecated
	public static void renewLogLevel(String className,String level){
		level = LogTool.getLevel(level.toUpperCase());
		Logger log = Logger.getLogger(className);
		log.setLevel(Level.toLevel(level));
	}
	
//	/**
//	 * it didn't work in weblogic
//	 * @param className
//	 * @param level
//	 */
//	@Deprecated
//	public static void renewLogLevelAll(Map<Object, ImsLogLevel> logLevelList){
//		
//		if (logLevelList.size() > 0) {
//			for (Map.Entry<Object, ImsLogLevel> entry : logLevelList.entrySet()) {
//				ImsLogLevel logLevel = entry.getValue();
//	            LogTool.renewLogLevel(logLevel.getClassName(), String.valueOf(logLevel.getLogLevel()));
//	        }
//		}
//	}
//	
	public static String getLevel(String level){
		if (level.equalsIgnoreCase(Level.DEBUG.toString())) {
			return level;
		}else if (level.equalsIgnoreCase(Level.INFO.toString())) {
			return level;
		}else if (level.equalsIgnoreCase(Level.ERROR.toString())) {
			return level;
		}
		
		if (level.matches("[0-9]")) {
			int levelInt = Integer.valueOf(level);
			switch (levelInt) {
			case 1:
				level = Level.DEBUG.toString();
				break;
			case 2:
				level = Level.INFO.toString();
				break;
			default:
				level = Level.ERROR.toString();
				break;
			}
		}
		
		if (level.matches("[[A-Z][a-z]]")) {
			if (level.equalsIgnoreCase("D")) {
				level = Level.DEBUG.toString();
			}else if (level.equalsIgnoreCase("I")) {
				level = Level.INFO.toString();
			}else {
				level = Level.ERROR.toString();
			}
		}
		
		return level;
	}
	
	private static <T> boolean isPrint(Class<T> clazz,int level){
		boolean isPrint = false;
	
		String className = clazz.toString().split(" ")[1];
		if (className.indexOf("$") > -1) {
			className = className.substring(0, className.indexOf("$"));
		}
		boolean flag = logLevelMap.get(className) == null;
		String levelString = String.valueOf(logLevelMap.get(className));
		
		if (flag || levelString.indexOf(String.valueOf(level)) > -1 ) {
			isPrint = true;
		}
		return isPrint;
	}
	
//	public static void put(DsIlogBusinessOperateLog businessOperateLog){
//		String log = JsonTool.toJson(businessOperateLog, "yyyy-MM-dd HH:mm:ss SSS");
//		byte[] bytesValue = SerializeTool.serialize(log);
//		JedisClient_outer2.rpush(ConstantTool.QUEUE_DS_BUSI_LOG.getBytes(), bytesValue);
//	}
	
	public static void main(String[] args) {
		String[] arr = {"1","2","3"};
		// PropertyConfigurator.configure("src\\resources\\log4j.properties");//读取外部配置文件
		LogTool.info(LogTool.class,"111");
		LogTool.info(LogTool.class, "222");
		
		LogTool.info(LogTool.class, "Temperature set to {}. Old temperature was {}. ", 1, 2);
		LogTool.info(LogTool.class, "set to a: {} b: {} b: {}", arr);
		
		String level = "1";
		System.out.println(LogTool.getLevel(level));
		System.out.println(LogTool.class.toString().split(" ")[1]);
		System.out.println();
	}
}