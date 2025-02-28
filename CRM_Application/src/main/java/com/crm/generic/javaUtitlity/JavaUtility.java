package com.crm.generic.javaUtitlity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.aventstack.extentreports.Status;
import com.crm.generic.webdriver.UtilityClassObject;

public class JavaUtility {

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

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, days);
		Date d = c.getTime();
		UtilityClassObject.getTest().log(Status.INFO, d.toString());
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		return format.format(d);
	}
}
