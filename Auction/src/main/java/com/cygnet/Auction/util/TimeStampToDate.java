package com.cygnet.Auction.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeStampToDate {

	long longDate;
	
	public String toDate(Date date) throws ParseException {
//		longDate = date.getTime();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//		String java_date = dateFormat.format(date);
//		return dateFormat.parse(java_date);
		return dateFormat.format(date);
	}
}
