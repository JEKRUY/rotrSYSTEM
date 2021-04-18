package net.jekruy.rotr.util;

import java.text.DecimalFormat;

import net.minecraft.world.World;

public class TDW {
	
	World world;
	int time;
	int DayTotal;
	String Week;
	int week;
	int month;
	String Month;
	int year;
	
	public TDW (World worldIn) {
		world = worldIn;
	}
	
	public String GetTime() {
		time = (int)Math.abs((world.getWorldTime()+ 6000) % 24000);
		if (((int)((float)time / 1000F)) > 9) {
			if (((time % 1000) * 6 / 100) > 9) {
				return ((int)((float)time / 1000F)) + ":" + ((time % 1000) * 6 / 100);
			} else {
				return ((int)((float)time / 1000F)) + ":0" + ((time % 1000) * 6 / 100);
			}
		} else {
			if (((time % 1000) * 6 / 100) > 9) {
				return "0" + ((int)((float)time / 1000F)) + ":" + ((time % 1000) * 6 / 100);
			} else {
				return "0" + ((int)((float)time / 1000F)) + ":0" + ((time % 1000) * 6 / 100);
			}
		}
	}
	
	public String GetDay() {
		return "" + (GetDayTotal() + 1 - (30 * (GetDayTotal() / 30)));
	}
	
	public String GetWeek() {
		week = (GetDayTotal() + 1 - ( 7 * (GetDayTotal() / 7)));
		switch (week) {
		case 1: Week = "Monday";
		break;
		case 2: Week = "Tuesday";
		break;
		case 3: Week = "Wednesday";
		break;
		case 4: Week = "Thursday";
		break;
		case 5: Week = "Friday";
		break;
		case 6: Week = "Saturday";
		break;
		case 7: Week = "Sunday";
		break;
		}
		return Week;
	}
	
	public String GetMonth() {
		month = ((GetDayTotal() / 30) + 1) - (12 * ((GetDayTotal() / 30) / 12));
		switch (month) {
		case 1: Month = "January";
		break;
		case 2: Month = "February";
		break;
		case 3: Month = "March";
		break;
		case 4: Month = "April";
		break;
		case 5: Month = "May";
		break;
		case 6: Month = "June";
		break;
		case 7: Month = "July";
		break;
		case 8: Month = "August";
		break;
		case 9: Month = "September";
		break;
		case 10: Month = "October";
		break;
		case 11: Month = "November";
		break;
		case 12: Month = "December";
		break;
		}
		return Month;
	}
	
	public String GetYear() {
		year = GetDayTotal() / 360 + 1;
		return "" + customFormat("0000", year);
	}
	
	public String customFormat(String pattern, double value ) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return (output);
	}
	
	public int GetWeather() {
		if (world.getWorldInfo().getCleanWeatherTime() > 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public int GetDayTotal() {
		DayTotal = (int)(world.getWorldTime() / 24000);
		return DayTotal;
	}

}
