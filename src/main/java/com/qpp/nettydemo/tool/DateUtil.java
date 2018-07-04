package com.qpp.nettydemo.tool;

import com.qpp.nettydemo.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getdate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	
	public static String getdate_yyyy_MM_dd(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		return df.format(new Date());
	}  
	public static String getdate_yyyy_MM_dd_HH_MM_SS(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}  
	public static String dateToTimeStamp(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		return df.format(date);
    }
	public static String getdate_yyyy_MM_dd_Hms(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(date);
	}
	public static Date getdate_yyyy_MM_dd_00_00_00(String startDate){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = null;
		try {
			date = df.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getYesterday(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	/**
	 * 将时间转化为本地星期
	 * @author Administrator
	 *
	 */

	public static void main(String[] args) throws ParseException {
	String currentTime = String.format("%tF%n", new Date(System.currentTimeMillis()));
	System.out.println("当前时间:"+currentTime);
	String result = getDayDiff("2016-02-28", currentTime);
	System.out.println("显示的时间格式:"+result);
	}
	/**
	* 得到时间差 yyyy-MM-dd 格式
	* @param time
	* @return
	* @throws ParseException
	*/
	public static String getDayDiff(String time) throws ParseException{
	String currentTime = String.format("%tF%n", new Date(System.currentTimeMillis()));
	return getDayDiff(time, currentTime);
	}
	/**
	* 得到时间差 yyyy-MM-dd 格式
	* @param fDateStr 需要计算的时间
	* @param oDateStr 应该传入当前时间
	* @return 
	* @throws ParseException
	*/
	public static String getDayDiff(String fDateStr, String oDateStr) throws ParseException{
	int result = daysOfTwo(fDateStr, oDateStr);
	String timeResult = "";
	switch (result) {
	case -1:
	timeResult = "请检查时间";
	break;
	case 0:
	timeResult = "今天";
	break;
	case 1:
	timeResult = "昨天";
	break;
	default:
	timeResult = String.format("%tA%n", getDateFormat(fDateStr));
	break;
	}
	if(Math.abs(result) > 7){//假如时间大于7天
	timeResult = fDateStr;
	}
	return timeResult;
	}
	/**
	* 判断时间相差几天
	* @return 时间为-1时，请检查代码
	* @throws ParseException 
	*/
	public static int daysOfTwo(String fDateStr, String oDateStr) throws ParseException {
	Date fDate = getDateFormat(fDateStr);
	Date oDate = getDateFormat(oDateStr);
	Calendar aCalendar = Calendar.getInstance();
	aCalendar.setTime(fDate);
	int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
	aCalendar.setTime(oDate);
	int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
	return day2 - day1;
	}


	/**
	* 将时间转换为 Date类型
	* @param time yyyy-MM-dd格式
	* @return
	* @throws ParseException 
	*/
	public static Date getDateFormat(String time) throws ParseException{
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	return sdf.parse(time);
	}

	/**
	 *    验证开始时间是否大于结束时间
	 *
	 * @author pengpai
	 * @date 2018/4/25 17:26
	 * @param startDate, endDate
	 * @return boolean
	 */
	public static boolean checkLongDate(String startDate, String endDate) {
		if (!ParaClick.clickString(startDate) && !ParaClick.clickString(endDate)) {
			Long start = 0L;
			Long end = 0L;
			start = DateUtil.getdate_yyyy_MM_dd_00_00_00(startDate + " 00:00:00").getTime();
			end = DateUtil.getdate_yyyy_MM_dd_00_00_00(endDate + " 23:59:59").getTime();
			if (start > end) {
				return false;
			}
			return true;
		}else if ((ParaClick.clickString(startDate)&&!ParaClick.clickString(endDate))
				|| (!ParaClick.clickString(startDate)&&ParaClick.clickString(endDate))) {
			return false;
		}else {
			return true;
		}
	}


}
