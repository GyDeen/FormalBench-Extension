import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MonthSeason {

	public static String monthSeason(String month, int days) {
		month = month.toLowerCase();

		if (month.equals("march") && 20 <= days || month.equals("april") || month.equals("may")
				|| (month.equals("june") && 20 >= days)) {
			return "spring";
		} else if (month.equals("june") && 21 <= days || month.equals("july") || month.equals("august")
				|| (month.equals("september") && 22 >= days)) {
			return "summer";
		} else if (month.equals("september") && 23 <= days || month.equals("october") || month.equals("november")
				|| (month.equals("december") && 20 >= days)) {
			return "autumn";
		} else {
			return "winter";
		}
	}
}
