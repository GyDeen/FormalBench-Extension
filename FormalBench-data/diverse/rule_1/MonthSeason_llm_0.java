import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MonthSeason {

	public static String monthSeason(String string, int day) {
		string = string.toLowerCase();

		if (string.equals("march") && day >= 20 || string.equals("april")
				|| string.equals("may") || (string.equals("june") && day <= 20)) {
			return "spring";
		} else if (string.equals("june") && day >= 21 || string.equals("july")
				|| string.equals("august")
				|| (string.equals("september") && day <= 22)) {
			return "summer";
		} else if (string.equals("september") && day >= 23
				|| string.equals("october") || string.equals("november")
				|| (string.equals("december") && day <= 20)) {
			return "autumn";
		} else {
			return "winter";
		}
	}
}
