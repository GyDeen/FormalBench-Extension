import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MonthSeason {

	public static String monthSeason(String month, int days) {
		month = month.toLowerCase();

		boolean year = month.equals("march") && days >= 20 || month.equals("april") || month.equals("may");
		boolean prev = month.equals("june") && days <= 20;
		boolean result = month.equals("june") && days >= 21 || month.equals("july")
				|| month.equals("august");
		boolean tmp = month.equals("september") && days <= 22;
		boolean temp = month.equals("september") && days >= 23 || month.equals("october")
				|| month.equals("november");
		if (year || (prev)) {
			return "spring";
		} else if (result || (tmp)) {
			return "summer";
		} else if (temp || (month.equals("december") && days <= 20)) {
			return "autumn";
		} else {
			return "winter";
		}
	}
}
