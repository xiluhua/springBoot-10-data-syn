package com.springBoot.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

public class DateTool
{
	public static final String DATE_MASK = "yyyy-MM-dd";
	public static final String DATE_TIME_MASK = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATE_TIME_MASK_1 = "yyyyMMddHHmmss";
	public static final String DATE_TIME_MASK_4= "yyyy-MM-dd HH:mm";
	public static final String DATE_TIME_MASK_5= "yyyy/MM/dd HH:mm";
	public static final String DATE_TIME_MASK_7= "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_TIME_MASK_6= "yyyy/MM/dd";
	public static final String TIME_PATTERN_MASK_1 = "HH:mm:ss";
	public static final String TIME_PATTERN_MASK_2 = "HH:mm";
	public static final String TIME_PATTERN_MASK_3 = "yyyy-MM";

	/**
	 * 设定时区
	 */
	static {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
	}
	/**
	 * 操作系统更改时区，始终显示北京时间
	 * @return
	 */
	public static Date today(){
		return new Date();
	}
	//测试
    public void testToday(){
    	System.out.println("testToday: "+today());
    }
	/**
     * 检查是否是今天
     * @param date - 某一日期
     */
    public static boolean isToday(Date date) {
        Date today = new Date();
        boolean result = false;
        String temp1 = getDateStrByFormat(date,"yyyy-MM-dd");
        String temp2 = getDateStrByFormat(today,"yyyy-MM-dd");
        if (temp1.equals(temp2)) {
        	result = true;
		}
        return result;
    }
    //测试
    public void testIsToday(){
    	Date date = DateTool.getDateByFormat("2013-02-22", "yyyy-MM-dd");
    	System.out.println("testIsToday: "+isToday(date));
    }
	/**
	 * 判断是否是闰年
	 */
	public static boolean IFLeapYear(int year)
	{
		if (year % 4 == 0)
			if (((year % 100 != 0 ? 1 : 0) | (year % 400 == 0 ? 1 : 0)) != 0)
				return true;
		return false;
	}
	//测试
    public void IFLeapYear(){
    	boolean IFLeapYear = DateTool.IFLeapYear(2013);
    	System.out.println("IFLeapYear: "+IFLeapYear);
    }
	/**
	 * 得到format格式的日期字符串dateStr
	 */
	public static String getDateStrByFormat(Date date,String format)
	{
		String dateStr = "";
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			dateStr = formatter.format(date);
		}
		return dateStr;
	}
	//测试
	public void testGetDateStrByFormat(){
		System.out.println(getDateStrByFormat(new Date(),"yyyy MM dd HH mm ss"));
	}

	/**
	 * 将字符串dateStr转换成日期
	 * @param dateStr
	 * @return
	 */
	public static Date convertStrToDate(String dateStr)
	{
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		} catch (Exception e) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateStr);
			} catch (ParseException e1) {
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
				} catch (ParseException e2) {
					try {
						date = new SimpleDateFormat("yyyy MM dd").parse(dateStr);
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}
			}
		}
		return date;
	}
	/**
	 * 将字符串dateStr转换成日期
	 * @param dateStr
	 * @return
	 */
	public static Date convertStrToDate2(String dateStr)
	{
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		} catch (Exception e) {
			try {
				date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateStr);
			} catch (Exception e4) {
				try {
					date = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(dateStr);
				} catch (Exception e5) {
					try {
						date = new SimpleDateFormat("yyyy/MM/dd").parse(dateStr);
					} catch (Exception e7) {
						try {
							date = new SimpleDateFormat("yy/MM/dd").parse(dateStr);
						} catch (Exception e6) {
							try {
								date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateStr);
							} catch (ParseException e1) {
								try {
									date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
								} catch (ParseException e2) {
									try {
										date = new SimpleDateFormat("yy-MM-dd").parse(dateStr);
									} catch (ParseException e8) {
										try {
											date = new SimpleDateFormat("yyyy MM dd").parse(dateStr);
										} catch (Exception e3) {
											e3.printStackTrace();
										}
									}
									
								}
							}
						}
					}
					
				}
			}
			
		}
		return date;
	}
	//测试
	public void testConvertStrToDate(){
		System.out.println("testConvertStrToDate: "+convertStrToDate("2013-02-22"));
	}
	/**
	 * 得到format格式的日期对象
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDateByFormat(String dateStr, String format)
	{
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	//测试
	public void testGetDateByFormat(){
		System.out.println("testGetDateByFormat: "+getDateByFormat("2013-02-22","yyyy-MM-dd"));
	}
	/**
	 * 将dateStr日期字符串按format重新格式化,生成新的字符串str
	 */
	public static String formatDateStrByFormat(String dateStr, String format)
	{
		String str = "";
		if (!StringUtils.isEmpty(dateStr)) {
			Date date = convertStrToDate(dateStr);
			str = getDateStrByFormat(date,format);
		}
		return str;
	}
	//测试
	public void testFormatDateStrByFormat(){
		System.out.println("testFormatDateStrByFormat1: "+formatDateStrByFormat("2013-02-22","yyyy-MM-dd HH:mm:ss"));
		System.out.println("testFormatDateStrByFormat2: "+formatDateStrByFormat("2013-02-22 10:10:10","yyyy-MM-dd HH:mm"));
	}
	/**
	 * 根据生日birthDay,获取年龄age
	 */
	public static int getAgeBybirthDay(Date birthDay)
	{
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			System.out.println("Attention Pease! The birthDay is before Now!");
			return 0;
		}

		int yearNow = cal.get(1);
		int monthNow = cal.get(2);
		int dayOfMonthNow = cal.get(5);
		
		cal.setTime(birthDay);
		int yearBirth = cal.get(1);
		int monthBirth = cal.get(2);
		int dayOfMonthBirth = cal.get(5);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return age;
	}

	/**
	 * 根据时间类型type,对日期时间做相加、相减处理;比如说，获取24小时后的日期值,获取10天后的日期值
	 */
	public static Date getCalendarDate(Date date, String type, int number)
	{
		type = type.toUpperCase();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (type.equalsIgnoreCase("YEAR"))
			calendar.add(Calendar.YEAR, number);
		else if (type.equalsIgnoreCase("MONTH"))
			calendar.add(Calendar.MONTH, number);
		else if (type.equalsIgnoreCase("DAY"))
			calendar.add(Calendar.DAY_OF_MONTH, number);
		else if (type.equalsIgnoreCase("HOUR"))
			calendar.add(Calendar.HOUR_OF_DAY, number);
		else if (type.equalsIgnoreCase("MINUTE"))
			calendar.add(Calendar.MINUTE, number);
		else if (type.equalsIgnoreCase("SECOND"))
			calendar.add(Calendar.SECOND, number);
		else
			System.out.println("输入的第2个参数有误,您有以下选择:YEAR;MONTH;DAY;HOUR;MINUTE;SECOND");
	
		return calendar.getTime();
	}

	/**
	 * 获取某年某月的所有日期
	 */
	public static List getDateList(String year, String month)
	{
		List datelist = new ArrayList();
		String[] bigMonth = { "01", "03", "05", "07", "08", "10", "12" };
		String[] smallMonth = { "04", "06", "09", "11" };
		try {
			if (StringTool.IFContain(bigMonth, month)) {
				for (int i = 1; i <= 31; i++) {
					Date tempdate = convertStrToDate(year + "-" + month + "-" + i);
					datelist.add(tempdate);
				}

			} else if (StringTool.IFContain(smallMonth, month)) {
				for (int i = 1; i <= 30; i++) {
					Date tempdate = convertStrToDate(year + "-" + month + "-" + i);
					datelist.add(tempdate);
				}

			} else if (IFLeapYear(Integer.parseInt(year))) {
				for (int i = 1; i <= 29; i++) {
					Date tempdate = convertStrToDate(year + "-" + month + "-" + i);
					datelist.add(tempdate);
				}
			} else {
				for (int i = 1; i <= 28; i++) {
					Date tempdate = convertStrToDate(year + "-" + month + "-" + i);
					datelist.add(tempdate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datelist;
	}

	/**
	 * 比较2个日期哪个前,哪个后
	 */
	public static boolean isDate1LateThanDate2(Date date1, Date date2)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		Date date11 = calendar.getTime();
		boolean flag = date11.after(date2);
		return flag;
	}

	 /**
     * 判断当前日期是否在两个日期之间
     * @param startDate 开始时间
     * @param endDate　结束时间
     * @return　
     */
    public static boolean betweenStartDateAndEndDate(Date startDate,Date endDate){
        boolean bool=false;
        
        return bool;
    }
    
	/**
	 * 将日期显示为汉字：
	 *     year + "年" + mon + "月" + day + "日" + hour + "时" + min + "分" + sec + "秒";
	 * @param date
	 * @return
	 */
	public static String formatTimeByChinese(Date date)
	{
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int mon = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);

		return year + "年" + mon + "月" + day + "日" + hour + "时" + min + "分" + sec + "秒";
	}

	/**
	 *  获取某月最后一天,返回字符串
	 */
	public static String getMonthLastDay(String date)
	{
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(convertStrToDate(date));
		c.set(Calendar.DATE, 1); //设为当前月的1号
		c.add(Calendar.MONTH, 1); //加一个月，变为下月的1号
		c.add(Calendar.DATE, -1); //减去一天，变为当月最后一天
		str = sdf.format(c.getTime());
		return str;
	}

	/**
	 * 获取某月第一天,返回字符串
	 */
	public static String getMonthFirstDay(String date)
	{
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(convertStrToDate(date));
		c.set(Calendar.DATE, 1); //设为当前月的1号            
		str = sdf.format(c.getTime());
		return str;
	}

	/**
	 * 获得本季度
	 */
	public static String getThisSeason(int month)
	{
		int season = 1;
		if (month >= 1 && month <= 3)
			season = 1;
		if (month >= 4 && month <= 6)
			season = 2;
		if (month >= 7 && month <= 9)
			season = 3;
		if (month >= 10 && month <= 12)
			season = 4;
		return String.valueOf(season);
	}

	/**
	 * 获取某年的所有星期天
	 */
	public static List getSundaysOfYear(String date, int number)
	{
		List list = new ArrayList();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date d = convertStrToDate(date);
		String temp = getDateStrByFormat(d,"yyyy");
		int year = Integer.valueOf(temp).intValue();
		c.set(year, 0, 1);

		while (true) {
			if (c.get(Calendar.YEAR) != year) {
				break;
			} else {
				if (c.get(Calendar.DAY_OF_WEEK) == number) {
					list.add(f.format(c.getTime()));
				}
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
			}
		}
		return list;
	}

	/**
	 * 判断日期是否存在,或合法
	 */
	public static boolean isDateExist(String dateStr, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String result = sdf.format(date); //判断转换前后两个字符串是否相等即可知道合不合法
		return result.equals(dateStr);
	}

	/**
	 * 日期字符串处理方法
	 */
	public static List dateStringHandler(List list, String format, String fieldName)
	{
		for (int i = 0; i < list.size(); i++) {
			Map m = (Map) list.get(i);
			if (m.get(fieldName) != null) {
				try {
					String dateStr = String.valueOf(m.get(fieldName));
					String strAfterHandled = DateTool.formatDateStrByFormat(dateStr, format);
					m.put(fieldName, strAfterHandled);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 根据输入的日期关键字,获取关键字指定的日期值
	 * YEAR:获取年
	 * MONTH:月
	 * DAY:日
	 * HOUR:时
	 * MINUTE:分
	 * SECOND:秒
	 */
	public static String getPartOfDate(Date date, String part)
	{
		String time = getDateStrByFormat(date,"yyyy MM dd HH mm ss");
		String[] timeArr = time.split(" ");
		if (part.equalsIgnoreCase("YEAR"))
			part = timeArr[0];
		else if (part.equalsIgnoreCase("MONTH"))
			part = timeArr[1];
		else if (part.equalsIgnoreCase("DAY"))
			part = timeArr[2];
		else if (part.equalsIgnoreCase("HOUR"))
			part = timeArr[3];
		else if (part.equalsIgnoreCase("MINUTE"))
			part = timeArr[4];
		else if (part.equalsIgnoreCase("SECOND"))
			part = timeArr[5];
		else
			System.err.println("输入的第2个参数有误,您有以下选择:YEAR;MONTH;DAY;HOUR;MINUTE;SECOND");
		return part;
	}
	//测试
	public void testGetPartOfDate(){
		System.err.println("testGetPartOfDate: "+getPartOfDate(today(), "DAY"));
	}
	
	/**
	 * 根据输入的数值year,month,date,hourOfDay,minute,second ，得到日期对象
	 * @param year
	 * @param month
	 * @param date
	 * @param hourOfDay
	 * @param minute
	 * @param second
	 * @return date
	 */
	public static Date getTime(int year, int month, int date, int hour, int minute, int second)
	{
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, hour, minute, second);
		return cal.getTime();
	}
	//测试
	public void testGetTime(){
		Date date = getTime(2013, 2, 22, 10, 10, 10);
		System.out.println("testGetTime: "+getDateStrByFormat(date, "yyyy-MM-dd HH:mm:ss"));
	}
	/**
	 * 根据日期类型,比较日期差 startDate,endDate
	 * @params: dateFormat : yyyy-MM-dd HH:mm:ss
	 * @params: type : 比较对象,SECOND,MINUTE,HOUR,DAY,WEEK,MONTH,YEAR
	 */
	public static long getDateDiff(Date startDate, Date endDate, String type)
	{
		long diff = 0;
		try {
			type = type.trim().toUpperCase();
			diff = endDate.getTime() - startDate.getTime(); //毫秒
			
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(diff);
			System.out.print(c.getTime());
			if (type.equals("SECOND")) {
				diff = diff / 1000; //秒
			} else if (type.equals("MINUTE")) {
				diff = diff / 60000; //分钟
			} else if (type.equals("HOUR")) {
				diff = diff / 3600000; //小时
			} else if (type.equals("DAY")) {
				diff = diff / 86400000; //天
			} else if (type.equals("WEEK")) {
				diff = diff / (86400000 * 7); //星期
			} else if (type.equals("MONTH")) {
				System.out.println(endDate.getMonth() + 1 + "\t" + startDate.getMonth() + 1);
				diff = (endDate.getMonth() + 1) + ((endDate.getYear() - startDate.getYear()) * 12) - (startDate.getMonth() + 1);
			} else if (type.equals("YEAR")) { //年
				diff = endDate.getYear() - startDate.getYear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}

	/**
	 * 根据日期类型,比较日期差 startDate,endDate
	 * @params: dateFormat : yyyy-MM-dd HH:mm:ss
	 * @params: type : 比较对象,SECOND,MINUTE,HOUR,DAY,WEEK,MONTH,YEAR
	 */
	public static long getDateStrDiff(String startDate, String endDate, String type,String format)
	{
		long diff = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date dateStart = sdf.parse(startDate); 	//example:1970-01-01 00:00:00
			Date dateEnd = sdf.parse(endDate); 		//example:1970-01-01 00:00:00
			type = type.trim().toUpperCase();
			//System.out.println(dateEnd.getTime()+"\t"+dateStart.getTime());
			diff = dateEnd.getTime() - dateStart.getTime(); //毫秒
			
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(diff);
			//System.out.print(c.getTime());
			if (type.equals("SECOND")) {
				diff = diff / 1000; //秒
			} else if (type.equals("MINUTE")) {
				diff = diff / 60000; //分钟
			} else if (type.equals("HOUR")) {
				diff = diff / 3600000; //小时
			} else if (type.equals("DAY")) {
				diff = diff / 86400000; //天
			} else if (type.equals("WEEK")) {
				diff = diff / (86400000 * 7); //星期
			} else if (type.equals("MONTH")) {
				System.out.println(dateEnd.getMonth() + 1 + "\t" + dateStart.getMonth() + 1);
				diff = (dateEnd.getMonth() + 1) + ((dateEnd.getYear() - dateStart.getYear()) * 12) - (dateStart.getMonth() + 1);
			} else if (type.equals("YEAR")) { //年
				diff = dateEnd.getYear() - dateStart.getYear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}
	//测试
	public static void testGetDateStrDiff()
	{		
		String startDate = "2013-01-01";
		String endDate = "2013-03-25";
		System.out.println("\ntestGetDateStrDiff： "+getDateStrDiff(startDate, endDate, "DAY", "yyyy-MM-dd"));
	}
	/**
	 * 根据毫秒second得到日期对象
	 */
	public static Date getDateBySecond(long second)
	{		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(second);
		return c.getTime();
	}
	//测试
	public static void testGetDateBySecond()
	{		
		Date date = getDateBySecond(new Long("1361545244745").longValue());
		System.out.println("testGetDateBySecond： "+getDateStrByFormat(date, "yyyy-MM-dd HH:mm:ss"));
	}
	/**
	 * 根据Date得到毫秒
	 */
	public static long getSecondByDate(Date date)
	{		
		return date.getTime();
	}
	//测试
	public void testGetSecondByDate(){
		System.out.println(getSecondByDate(today()));
	}
	/**
	 * 得到中文星期
	 * @param date
	 * @return
	 */
	private static String getDayWeekChinese(Date date) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int week=calendar.get(Calendar.DAY_OF_WEEK);
		switch(week) {
			case 1: return "星期日";
			case 2: return "星期一";
			case 3: return "星期二";
			case 4: return "星期三";
			case 5: return "星期四";
			case 6: return "星期五";
			case 7: return "星期六";
			default : return "";
		}
	}
	
	public static String formatLocaleUS(String dateStr,String format){
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);//MMM dd hh:mm:ss Z yyyy
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
        	dateStr = formatter.format(sdf.parse(new Date().toString()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return dateStr;
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub    //2010-02-31 
		//    	List list = getSundaysOfYear("2011-1-1", 7);
		//    	for (int i = 0; i < list.size(); i++)
		//		{
		//			System.out.println(list.get(i)+"==="+getWeek(String.valueOf(list.get(i))));
		//		}
		//    	System.out.println(isDateExist("20100229","yyyyMMdd")+"====isexist===");
		//    	System.out.println(getPresentDate());
		//    	System.out.println(getPresentDateStr("yyyy MM dd hh mm ss"));
		//    	String time = getPresentDateStr("yyyy MM dd HH:mm:ss");
		//Date endTime = getEndTime(2012, 2, 19, 17, 36, 30);
		//Date startTime = new Date();
		//   	Date endTime = new Date();
		//System.out.println(DateTool.isDate1LateThanDate2(startTime, endTime));
		//    	System.out.println(getPartOfDate(new Date(),"hour"));
		//    	System.out.println(DateTool.getFormatDate("yyyy-MM-dd HH:mm:ss", endTime));
		//    	System.out.println(DateTool.getFormatDate("yyyy-MM-dd HH:mm:ss", getCalendarDate(endTime,"DAY",1)));

		//    	Date date1=getEndTime(2012,3,10,17,36,30);
		//    	Date date2=getEndTime(2012,3,11,17,36,30);
		//    	System.out.println(isDate1EqualsDate2WithSomedays(date1, date2,0));
		//String  DateTool.getFormatDate("", date);
//		String dateEnd = DateTool.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date());
		//必须要是1970-01-01 08:00:00,因为时区TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
//		System.out.println(DateTool.getDateDiff("1970-01-01 08:00:00", dateEnd, "seconddd") + "\n" + dateEnd);
		//ateTool.getDateBySecond();
		System.out.println(DateTool.getDayWeekChinese(DateTool.today()));
//		int num = 60;
//		Date date = getDateAdd(new Date(), Calendar.DATE, -num);
//		System.out.println(date);
		System.out.println("------------------------------------------");
		System.out.println(DateTool.getDateStrDiff("2015-10-10", "2016-01-01", "DAY", "yyyy-MM-dd"));
		System.out.println(convertStrToDate("2013-1-01"));
		
		boolean IFLeapYear = DateTool.IFLeapYear(1900);
    	System.out.println("IFLeapYear: "+IFLeapYear);
    	
    	String dateStr = DateTool.formatLocaleUS(new Date().toString(), DateTool.DATE_TIME_MASK);
    	System.out.println("dateStr:"+dateStr);
	}
}