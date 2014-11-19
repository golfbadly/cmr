package com.xplink.random_cm.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.xplink.random_cm.common.Constant;



public class DateUtil {
	// Set first date of month
	
	public static  Locale LOCALE_TH = new Locale("th", "TH");
	public static SimpleDateFormat FORMAT_DATE = new SimpleDateFormat(Constant.DATE_FORMAT, LOCALE_TH);
	
	private static Calendar getCalendar() {
		return Calendar.getInstance(LOCALE_TH);
	}
	
	public static Date getTomorrowDate() {
		Calendar calendar = getCalendar();
	
		calendar.add(Calendar.DATE,1); 
	
		Date tomorrowDate = calendar.getTime();
		
		return tomorrowDate;
	}
	
	
	public static Date getTodayDate() {
		Calendar calendar = getCalendar();
		
	
		Date todayDate = calendar.getTime();
	
		return todayDate;
	}
	
	public static Date getNextWeekDate(){
		Calendar calendar = getCalendar();
		calendar.add(Calendar.DATE,7);
		Date date = calendar.getTime();
		return date;
	}
	
	public static Date getStartCheckDate(Date startDate,int dayIcon){
		Calendar calendar = getCalendar();
		calendar.setTime(startDate);
		calendar.add(Calendar.DATE, +dayIcon);
		Date startCheckDate = calendar.getTime();
		return startCheckDate;
	}
	public static Timestamp getSysDate() {

		Timestamp nowDate = new Timestamp(new Date().getTime());

		return nowDate;
	}
	
	public static String getTodayDateStrign(){
		String DATE_FORMAT = Constant.DATE_FORMAT;
		SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE));
		return (String) sdf.format(calendar.getTime());
	}
	
	public static String getNextDate(int days){
		String DATE_FORMAT = Constant.DATE_FORMAT;
		SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE));
		//Set date to next 
		calendar.add(Calendar.DATE, days);
		return (String) sdf.format(calendar.getTime());
	}


}
