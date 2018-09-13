package hello.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormat {
	public static String formatTime(Date d) {
		// SimpleDateFormat sdfDate = new SimpleDateFormat("HH'h'mm");
		SimpleDateFormat sdfDate = new SimpleDateFormat("MMM d, HH'h'mm", Locale.ENGLISH);
		return sdfDate.format(d);
	}
	
//	public static String formatTime(long timestamp) {
//		SimpleDateFormat sdfDate = new SimpleDateFormat("dd HH'h'mm");
//		return sdfDate.format(new Date(timestamp));
//	}
	
//	public static void main(String[] args) {
//		System.out.println(TimeFormat.formatTime(new Date()));
//		// System.out.println(TimeFormat.formatTime(1534414030302L));
//	}
}
