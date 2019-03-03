package com.springBoot.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XLH
 * createDate 2011-12-1
 */
public class StringTool
{
	/**
	 * 判断String是否为空,或为""
	 * @param str
	 * @return true
	 */
	public static boolean isNull(String str)
	{	    
		boolean flag = false;	    
	    if (str == null || str.trim().equals(""))
	    	flag = true;
	    return flag;
	}
	public static String ObjToString(Object obj){
		String  str=null;
		if(obj==null){
			str="";
		}
		else{
			Class clazz=obj.getClass();
			if(clazz.equals(Integer.class)){
				str=obj.toString();
			}
			else if(clazz.equals(int.class)){
				str=obj+"";
			}
			else if(clazz.equals(Long.class)){
				str=obj.toString();
			}
			else if(clazz.equals(long.class)){
				str=obj+"";
			}
			else if(clazz.equals(Double.class)){
				str=obj.toString();
			}
			else if(clazz.equals(double.class)){
				str=obj+"";
			}
			else if(clazz.equals(Date.class)){
				str=DateTool.getDateStrByFormat((Date)obj,"yyyy-MM-dd HH:mm:ss:SSS");
			}
			else if(clazz.equals(String.class)){
				str=obj+"";
			}
			else if(clazz.getSimpleName().endsWith("Exception")){
				
				str=LogTool.getExceptionStr((Exception)obj);
				
			}
			else{
				//不支持类型
				LogTool.info(StringTool.class,"不支持类型:"+clazz.getName());
				str="";
			}
		}
		return str;
	}
	//测试
	public void testIsNull()
	{
		String str1 = " ";
		String str2 = null;
		String str3 = "3 ";
		System.out.println("testIsNull1: "+isNull(str1));
		System.out.println("testIsNull2: "+isNull(str2));
		System.out.println("testIsNull3: "+isNull(str3));
	}
	
	/**
	 * 判断数组strArray内,是否含有str
	 * @param strArray
	 * @param str
	 * @return true/false
	 */
	public static boolean IFContain(String[] strArray, String str)
	{
		boolean result = false;
		if (strArray != null && strArray.length > 0 && !isNull(str)) {
			for (int i = 0; i < strArray.length; i++) {
				if (strArray[i].equals(str)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	//测试
	public void testIFContain()
	{
		String[] strArray = {"a","b","c"};
		String str = "d";
		System.out.println("testIFContain: "+IFContain(strArray, str));
	}
	
	/**
	 * 判断当前字符串数组元素项是否有重复
	 * @param str 字符串数组元素
	 * @return true/false
	 */
	public static boolean isArrayRepeated(String[] str) {
		boolean flag = false;
		// 符合条件，则进行处理
		if (str != null && str.length != 0) {
			for (int i = 0; i < str.length; i++) {
				if (flag)
					break;				
				for (int j = i + 1; j < str.length; j++) 
				{
					if (!isNull(str[i]) && str[i].equals(str[j])) {
						flag = true;
						break;
					}
				}
			}
		}
		
		return flag;
	}
	
	//测试
	public void testIsArrayRepeated()
	{
		String[] strArray = {"a","b","c","b"};
		System.out.println("testIsArrayRepeated: "+isArrayRepeated(strArray));
		
	}
	
	/**
	 * 删除str中的所有subStr字符
	 * @param str
	 * @param subStr
	 * @param replacement ： subStr的替身，如果是""就是删除subStr
	 * @return str
	 */
	public static String removeAll(String str,String subStr,String replacement)
	{
		int countSubstr = countSubstr(str,subStr);
		for (int i = 0; i < countSubstr; i++) {
			str = StringTool.replaceSubstrByIndex(str, subStr, replacement, 1);
		}
		return str;
	}
	//测试
	public void testRemoveAll()
	{
		String str = "abc?def??hij?klmn?";
		System.out.println("testRemoveAll: "+removeAll(str,"abc","+"));
	}
	
	/**
	 * 获取str包含subStr的次数
	 * @param str
	 * @param subStr
	 * @return
	 */
	public static int countSubstr(String str,String subStr)
	{		
		if(str.length() < subStr.length()) 
			return 0 ;
		int count = 0;
	    int offset = 0;
	    int lastIndex = str.lastIndexOf(subStr);
	    
	    if(str.indexOf(subStr)!=-1)
        {
		    do {
	        	offset = str.indexOf(subStr, offset);	        	
	            count++;
	        	offset += subStr.length();	        	
	        }while (offset <= lastIndex);			//如果没有到母字符串末尾,则继续循环        	    
		}
	    return count;
	}
	
	//测试
	public void testCountSubstr()
	{
		String str = "abc?def??hij?klmn?";
		System.out.println("testCountSubstr: "+countSubstr(str,"?"));
	}
	
	/**
	 * 获取subStr第n次出现在str的的位置(index)：
	 * @param str
	 * @param subStr
	 * @param showTime 出现的位置
	 * @return
	 */
	public static int getPositionByShowTime(String str,String subStr,int showTime)
	{
		if(str.length() < subStr.length())  //如果subStr长度大于str,或n超出总的出现次数,
			return -1 ;						//返回没有
		int count = 0;
	    int offset = 0;
	    int lastIndex = str.lastIndexOf(subStr);	    
	    if(str.indexOf(subStr)!=-1)
        {
		    do {
	        	offset = str.indexOf(subStr, offset);	        	
	            count++;
	            if(showTime == count) 
	            	break;
	        	offset += subStr.length();	        	
	        }while (offset <= lastIndex);	//如果没有到母字符串末尾,则继续循环        	    
		}
	    return offset;
	}
	
	//测试
	public void testGetPositionByShowTime()
	{
		String str = "abc?def??hij?klmn?";
		System.out.println("getPositionByShowTime: "+getPositionByShowTime(str,"?",3));
	}
	
	/**
	 * 用fields依次替换str中的subStr
	 * @param str
	 * @param subStr
	 * @param fields
	 * @return 
	 */
	public static String replaceByArr(String str,String subStr,String[] fields)
	{
		for (int i = 0; i < fields.length; i++)		
			str = replaceSubstrByIndex(str,subStr, fields[i],1);								
		return str;
	}
	
	//测试
	public void testReplaceByArr()
	{
		String str = "abc?def??hij?klmn?";
		System.out.println("testReplaceByArr: "+replaceByArr(str,"?",new String[]{"1","1","1","1"}));
	}
	
	/**
	 * 用replacement替换or删除str中第N个subStr
	 * @param str
	 * @param subStr
	 * @param replacement
	 * @param n
	 * @return
	 */
	public static String replaceSubstrByIndex(String str,String subStr,String replacement,int n)
	{
		int t = getPositionByShowTime(str, subStr, n);
		StringBuffer buffer = new StringBuffer();
		String s = str.substring(0,t);		
		String sss = str.substring(t+subStr.length());
		buffer.append(s).append(replacement).append(sss);
		return buffer.toString();
	}
	
	//测试
	public void testReplaceSubstrByIndex()
	{
		String str = "abc?def??hij?klmn?";
		System.out.println("testReplaceSubstrByIndex: "+replaceSubstrByIndex(str,"?","z",1));
	}
	/**
	 * 得到n个str连接起来的字符串。 比如 n＝3：str为"a1"，则返回"a1a1a1"。 str为"0"，则返回"000"。
	 * 以下情况返回null： 1、n <= 0 或者 2、str == null
	 * @param n 重复次数
	 * @param str 将被重复的字符串
	 */
	public static String repeatStr(int n, String str) {
		String repeatStr = "";
		// 符合条件，则进行处理
		if (n > 0 && str != null) {
			int length = str.length();
			if (length == 0) {		
				repeatStr = "";	// str为空字符串，则返回空字符串		
			} else {
				// 执行到此repeatTime> 0 && intMetatStringLength > 0
				StringBuffer buffer = new StringBuffer(n * length);
				for (int i = 0; i < n; i++) {
					buffer.append(str);
				}
				repeatStr = buffer.toString();
			}
		} 
		return repeatStr;
	}
	
	//测试
	public void testRepeatStr()
	{
		String str = "a";
		System.out.println("testReplaceSubstrByIndex: "+repeatStr(3,str));
	}
	
	/**
	 * 将超长字符串，以指定的替换符替代 比如 getBriefString(2,"我爱祖国",">>"),则返回"我爱>>"
	 * @param requiredLength 指定长度
	 * @param metaString 源字符串
	 * @param delim 替换字符
	 * @return 如果源字符串小于指定长度，则返回源字符串，否则以替换后的字符串返回
	 */
	public static String getBriefString(int requiredLength, String metaString,String replace) 
	{
		String brief = "";
		// 符合条件，则进行处理
		if (requiredLength > 0 && metaString != null && replace != null) {
			if (metaString.length() > requiredLength) {
				brief = metaString.substring(0, requiredLength) + replace;
			} else {
				brief = metaString;
			}
		}
		return brief;
	}
	/**
	 * 将字符串分割成子串List。 不包括空白字符串（" "）
	 * @param metaString
	 * @param token
	 * @return
	 */
	public static List divStrIntoList(String metaString, String token)
	{
	    List list = new ArrayList(); // 返回的结果参数
	    String temp = null; 		 // 存放分割字符串
	    StringTokenizer stringTokenizer = new StringTokenizer(metaString, token);
        while (stringTokenizer.hasMoreTokens())
        {
            temp = stringTokenizer.nextToken();
            list.add(temp);
        }
	    return list;
	}
	/**
	 * 将输入的字串左补0到指定位数
	 * @param path
	 * @param readLength
	 * @return
	 */
	public static String addZero(String str, int totalSize) 
	{
		if (str == null)
			str = "";
		int length = str.length();
		if(totalSize == length)
			return str;
		if(totalSize < length)
			return str.substring(length-totalSize,length-1);		
		for (int i = 0; i < totalSize - length; i++) {
			str = "0" + str;
		}
		return str;
	}	
	/**
	 * 返回字符串的副本，忽略前导'0'。
	 * @param str
	 * @return
	 */
	public static String trimLeadingZero(String str)
	{
		if(str == null || str.length() == 0)
		return str;		
		for(int i=0;i<str.length();i++)
		{			
			if(str.charAt(0) == '0')
				str = str.substring(1);				
			else
				break;
			i = 0;
		}
		return str;
	}
	/**
	 * 获取low-->range(包括range)范围内的随机数
	 * @param low
	 * @param range
	 * @return
	 */
	public static int getRandomInRange(int low,int range)
	{		
		int key = -999999999;
		Random random = new Random();
		while(key < low)
		{
			key = random.nextInt(range);
		}
		return key;
	}
	
	/**
	 * 获取指定长度的随机数
	 * @param length 长度
	 * @return
	 */
	public static String getDesignedLengthRandom(int length)
	{		
		return String.valueOf(Math.random()).substring(2,length+2);
	}
	//测试
	public void testGetDesignedLengthRandom()
	{
		System.out.println("testGetDesignedLengthRandom: "+getDesignedLengthRandom(6));
	}
	/**
	 * 判断是否包含汉字
	 * @param str
	 * @return
	 */
	public static boolean isHasChinese(String str)
	{
		boolean flag = str.getBytes().length != str.length();
		return flag;
	}
	/**
	 * 判断包含几个汉字
	 * @param str
	 * @return
	 */
	public static int getChineseNum(String str)
	{
		int count = 0;
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find())
		{
		    for (int i = 0; i <= m.groupCount(); i++)
		    {
		        count = count + 1;
		    }		    
		}
		return count;
	}
	/**
	 * path:文件路径
	 * readLength:读取文件长度
	 */
	public String getCharset(String path,int readLength) 
	{		
		String charset = ""; 
		try {
			String[] charsetArray = new String[]{"GB2312","GBK","UNICODE","UNICODE BIG ENDIAN","ISO-8859-1","UTF-8","ANSI"};
			FileInputStream fis = new FileInputStream(new File(path)); 
			//文件没有给到长度长,就直接读取文件全部
			if(fis.available() < readLength) readLength = fis.available();
			byte[] buf = new byte[readLength]; 
			fis.read(buf); 
			
			String str = new String(buf); 			
			String strUNICODE = new String(buf, "UNICODE"); 				       
	   
        	for (int i = 0; i < charsetArray.length; i++) {
        		if (str.equals(new String(str.getBytes(charsetArray[i]), charsetArray[i]))) {
        			charset = charsetArray[i];
	                break;
	            }
			}
        	//如果用UNICODE,字符串没有长度,就可以判断是UTF-8
        	if (charset.equals("UNICODE") && strUNICODE.length() == 0){
        		charset = "UTF-8";
        	}        	
		} catch (Exception e) {
			charset = "ANSI";
		}
		System.out.println("charset1==>"+charset);
		//linux系统的话,GB2312和UTF-8正好相反,所以需要转换
		if (!getOS().equals("windows"))
    	{
    		if (charset.equals("UTF-8")) 
    			charset = "GB2312";    		
    		else if (charset.equals("GB2312") || charset.equals("gbk") || charset.equals("ANSI")) 
    			charset = "UTF-8";    		
    	}
		System.out.println("charset3==>"+charset);
        return charset;
    } 
	/************************* unicode编码开始 *************************/
	//把统一码转换成String
	public static String UnicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");    
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replaceAll(matcher.group(1), ch + "");    
        }
        return str;
    }
	/**
     * 将字符串编码成 Unicode 。
     * @param theString 待转换成Unicode编码的字符串。
     * @param escapeSpace 是否忽略空格。
     * @return 返回转换后Unicode编码的字符串。
     */
    public String toUnicode(String theString, boolean escapeSpace) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);
        for(int x=0; x<len; x++) {
            char aChar = theString.charAt(x);
            // Handle common case first, selecting largest block that
            // avoids the specials below
            if ((aChar > 61) && (aChar < 127)) {
                if (aChar == '\\') {
                    outBuffer.append('\\'); outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(aChar);
                continue;
            }
            switch(aChar) {
                case ' ':
                    if (x == 0 || escapeSpace)
                        outBuffer.append('\\');
                    outBuffer.append(' ');
                    break;
                case '\t':outBuffer.append('\\'); outBuffer.append('t');
                          break;
                case '\n':outBuffer.append('\\'); outBuffer.append('n');
                          break;
                case '\r':outBuffer.append('\\'); outBuffer.append('r');
                          break;
                case '\f':outBuffer.append('\\'); outBuffer.append('f');
                          break;
                case '=': // Fall through
                case ':': // Fall through
                case '#': // Fall through
                case '!':
                    outBuffer.append('\\'); outBuffer.append(aChar);
                    break;
                default:
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >>   8) & 0xF));
                        outBuffer.append(toHex((aChar >>   4) & 0xF));
                        outBuffer.append(toHex( aChar         & 0xF));
                    } else {
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }
    private static final char[] hexDigit = {
	    '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
	};
	
	private static char toHex(int nibble) {
	    return hexDigit[(nibble & 0xF)];
	}
	/************************* unicode编码结束 *************************/  
	/**
	 * 去除所有HTML标记
	 */
	public String getStrWithoutHtml(String HTMLStr)
    {
    	String strWithoutHtml = HTMLStr.replaceAll("<[^>]*>","");
    	strWithoutHtml = strWithoutHtml.replaceAll("&nbsp;", "");    	
    	return strWithoutHtml;
    }
	/**
	 * 去除HTML中的外链
	 * @param htmlStr
	 * @return
	 */
	public static String removeHTMLAnchor(String htmlStr)
	{		
		String htmlStrWithoutAnchor = htmlStr.replaceAll( "<(/)?a(.*?)>",   " ");
		return htmlStrWithoutAnchor;
	}
	/**
	 *  获取当前操作系统
	 */
	public static String getOS(){
		String system="";
		int OS=System.getProperty("os.name").toUpperCase().indexOf("WINDOWS");
		if(OS!=-1)//windows
			system="windows";
		else
			system="linux";	
		return system;
	}
	
	//测试
	public void testGetOS()
	{
		System.out.println("testGetOS: "+getOS());
	}
	/**
	 * 执行操作系统命令
	 */
	public static void exeCommand(String cmd) throws Exception{
		Runtime.getRuntime().exec(cmd);//exec参数为命令
		/*常用DOS命令：
		 OS.exeCommand("shutdown -s"); //关机
	     OS.exeCommand("cmd /c del c:\\a.doc"); //删除文件	
		 OS.exeCommand("cmd /c rd c:\\test  /s /q");//删除目录
		 OS.exeCommand("c:\\windows\\system32\\notepad.exe C://a.txt");//打开记事本 
		 */
	}
	/**
	 * 使字符串首字母大写
	 */
	public String upperCaseFirstLetter(String str)
	{
		String firstLetter = str.substring(0,1).toUpperCase();
		String firstLetterUpperCasedStr = firstLetter + str.substring(1);
		return firstLetterUpperCasedStr;		
	}
	
	public static boolean checkApp_nameNew(String app_name) throws UnsupportedEncodingException {
		
		boolean isApp_nameNewOK = true;
		boolean isLengthOK = false;
		if (app_name.length() >= 2 && app_name.length() <=24) {
			isLengthOK = true;
		}
		
		String regex = "^[a-zA-Z0-9[\u4e00-\u9fa5]]$";
		String signRegex = "[\\[\\]~!@#$%^&*()_+}{|\":?.,';=-\\><、。，‘；】【——）（……%￥！◎※§ ]";
		
		for (int i = 0; i < app_name.length(); i++) {
			String temp = app_name.substring(i,i+1);
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(temp);
			boolean result1 = m.find();
			
			System.out.println(temp+","+result1);
			if (!result1) {
				p = Pattern.compile(signRegex);
				m = p.matcher(temp);
				boolean result2 = m.find();
				if (!isHasChinese(temp) || result2) {					
					isApp_nameNewOK = false;
					System.out.println(temp);
					break;
				}
			}
		}
		
		if (isApp_nameNewOK && isLengthOK)
			return true;
		else
			return false;
	}
	
	/**
	 * 获取数字start-end之间的所有质数
	 * @param start
	 * @param end
	 * @return
	 */
	public static List getZhiNumList(int start,int end)
	{
		int i,j; 
		List zhiNumList = new ArrayList();
		for (i = 1; i <= 100; i++){ 
			for (j = 2; j < i; j++){ 
				if (i % j == 0) 
					break; 
			} 
			if (j >= i){
				zhiNumList.add(String.valueOf(i));
			}
		}
		
		System.out.println(zhiNumList);
		return zhiNumList;
	}
	
	/**
    * 全角字符变半角字符
    * @param str
    * @return
    */
	public static String full2Half(String str)
	{
		if (str == null || "".equals(str))
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 65281 && c < 65373)
				sb.append((char) (c - 65248));
			else
				sb.append(str.charAt(i));
		}

		return sb.toString();
	}

	//测试
	public void testFull2Half()
	{
		System.out.println("testFull2Half: "+full2Half("？："));
	}
	
	 /**
     * 页面的非法字符检查
     * @param str
     * @return
     */
    public static String replaceStr(String str) {
		if (str != null && str.length() > 0) {
			str = str.replaceAll("~", ",");
			str = str.replaceAll(" ", ",");
			str = str.replaceAll("　", ",");
			str = str.replaceAll(" ", ",");
			str = str.replaceAll("`", ",");
			str = str.replaceAll("!", ",");
			str = str.replaceAll("@", ",");
			str = str.replaceAll("#", ",");
			str = str.replaceAll("\\$", ",");
			str = str.replaceAll("%", ",");
			str = str.replaceAll("\\^", ",");
			str = str.replaceAll("&", ",");
			str = str.replaceAll("\\*", ",");
			str = str.replaceAll("\\(", ",");
			str = str.replaceAll("\\)", ",");
			str = str.replaceAll("-", ",");
			str = str.replaceAll("_", ",");
			str = str.replaceAll("=", ",");
			str = str.replaceAll("\\+", ",");
			str = str.replaceAll("\\{", ",");
			str = str.replaceAll("\\[", ",");
			str = str.replaceAll("\\}", ",");
			str = str.replaceAll("\\]", ",");
			str = str.replaceAll("\\|", ",");
			str = str.replaceAll("\\\\", ",");
			str = str.replaceAll(";", ",");
			str = str.replaceAll(":", ",");
			str = str.replaceAll("'", ",");
			str = str.replaceAll("\\\"", ",");
			str = str.replaceAll("<", ",");
			str = str.replaceAll(">", ",");
			str = str.replaceAll("\\.", ",");
			str = str.replaceAll("\\?", ",");
			str = str.replaceAll("/", ",");
			str = str.replaceAll("～", ",");
			str = str.replaceAll("`", ",");
			str = str.replaceAll("！", ",");
			str = str.replaceAll("＠", ",");
			str = str.replaceAll("＃", ",");
			str = str.replaceAll("＄", ",");
			str = str.replaceAll("％", ",");
			str = str.replaceAll("︿", ",");
			str = str.replaceAll("＆", ",");
			str = str.replaceAll("×", ",");
			str = str.replaceAll("（", ",");
			str = str.replaceAll("）", ",");
			str = str.replaceAll("－", ",");
			str = str.replaceAll("＿", ",");
			str = str.replaceAll("＋", ",");
			str = str.replaceAll("＝", ",");
			str = str.replaceAll("｛", ",");
			str = str.replaceAll("［", ",");
			str = str.replaceAll("｝", ",");
			str = str.replaceAll("］", ",");
			str = str.replaceAll("｜", ",");
			str = str.replaceAll("＼", ",");
			str = str.replaceAll("：", ",");
			str = str.replaceAll("；", ",");
			str = str.replaceAll("＂", ",");
			str = str.replaceAll("＇", ",");
			str = str.replaceAll("＜", ",");
			str = str.replaceAll("，", ",");
			str = str.replaceAll("＞", ",");
			str = str.replaceAll("．", ",");
			str = str.replaceAll("？", ",");
			str = str.replaceAll("／", ",");
			str = str.replaceAll("·", ",");
			str = str.replaceAll("￥", ",");
			str = str.replaceAll("……", ",");
			str = str.replaceAll("（", ",");
			str = str.replaceAll("）", ",");
			str = str.replaceAll("——", ",");
			str = str.replaceAll("-", ",");
			str = str.replaceAll("【", ",");
			str = str.replaceAll("】", ",");
			str = str.replaceAll("、", ",");
			str = str.replaceAll("”", ",");
			str = str.replaceAll("’", ",");
			str = str.replaceAll("《", ",");
			str = str.replaceAll("》", ",");
			str = str.replaceAll("“", ",");
			str = str.replaceAll("。", ",");
		}
		return str;
    }

    /**
     * 将字符串str从srcEncode转换成编码targetEncode
     * @param str
     * @param srcEncode
     * @param targetEncode
     * @return str，失败返回null
     */
	public static String changeEnCoding(String str,String srcEncode,String targetEncode)
	{
		try {
			 str= new String(str.getBytes(srcEncode), targetEncode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			str = null;
		}
		return str;
	}

	 /**
     * 页面中去除字符串中的空格、回车、换行符、制表符
     *
     * @author shazao
     * @date 2007-08-17
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {

		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		return str;
	}
	
    public String getBirthdayByIdno(String idno){
		
		String birthday = "99999999";
		if (idno.length() == 15) {
			birthday = "19"+idno.substring(6, 12);
		}
		else if (idno.length() == 18) {
			birthday = idno.substring(6, 14);
		}
		
		return birthday;
	}
	
	public String getSexByIdno(String idno){
		
		String sex = "";
		if (idno.length() == 15) {
			sex = idno.substring(14);
		}
		else if (idno.length() == 18) {
			sex = idno.substring(16, 17);
		}
		
		if (new Integer(sex).intValue()%2 == 0) {
			sex = "2";
		}else {
			sex = "1";
		}
		
		return sex;
	}
	
	/**
	 * 获取本机IP地址
	 * @return
	 */
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
			System.out.println(netInterface.getName());
			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				
				if (ip != null && ip instanceof Inet4Address) {

					System.out.println("local IP address = " + ip.getHostAddress());
					localIp = ip.getHostAddress();
					if (!localIp.equals("127.0.0.1")) {
						return localIp;
					}
					
				}
			}
		}
		return localIp;
	}
	
	public static void main(String[] args) throws SocketException
	{
		// String s1 = "my";
		// String s2 = "mydrmyeammymy";
		// System.out.println("------------->"+StringTool.replaceSubstrByIndex(s2,s1,"",1));
		// System.out.println(StringTool.addZero("3", 4));
		// String
		// sql="update E_DM_TRANS set 1 = ? ,2 = ? ,3 = ? ,4 = ? ,5 = ? ";
		// System.out.println("==before==>"+sql);
		// String
		// sql2="update E_DM_TRANS set 1 = z ,2 = z ,3 = z ,4 = z ,5 = z ";
		// String removeAllSubstr = removeAll(sql, "?", "");
		// System.out.println(removeAllSubstr);
		// String[] fields = new String[]{"1","2","3","4","5"};
		// String temp = replaceByArr(sql, "?", fields);
		// System.out.println(temp+"====");
		// String replace = replaceSubstrByIndex(sql, "?", "t", 2);
		// System.out.println("==replace==>"+replace);
		 System.out.println(getRandomInRange(20, 100));
		// String str = "北京市010上海市021天津市022";//"w问问sssfsf三翻四复s";
		// System.out.println(isHasChinese(str));
		// System.out.println(getChineseNum(str));
		// System.out.println(divStrIntoListByRegex(str, RegExpTool.allNumRegex,
		// RegExpTool.allCineseRegex));
		// String path =
		// "<a href=>F:\\ApacheSoftwareFoundation\\Tomcat5.5\\webapps\\web.itaiping.com\\huodong/springOuting/1332488310796847136.jpg</a>";
		// System.out.println(removeHTMLAnchor(path));
		// for (int i = 0; i < 100; i++) {
		// System.out.println(getRandomInRange(0,26));
		// }
		//
		String ip = StringTool.getLocalIp();
		System.out.println(ip);
		 
	}
	
	
}