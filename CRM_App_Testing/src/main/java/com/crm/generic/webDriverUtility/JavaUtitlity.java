package com.crm.generic.webDriverUtility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;

public class JavaUtitlity {

	public int getRandomNumber(int range) {
		Random random = new Random();
		return random.nextInt(range);
	}
	
	public String getCurrentDateTime() {
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_YY_hh_mm_ss"));
		return date;
	}
	
	public String getFormattedDate(String input) {
		LocalDate localDate = LocalDate.parse(input);
		return localDate.toString();
	}
	
	public String getRequiredDate(int days) {
		Calendar calendar = new SimpleDateFormat("yyyy-MM-dd").getCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime().toString();
	}
}













