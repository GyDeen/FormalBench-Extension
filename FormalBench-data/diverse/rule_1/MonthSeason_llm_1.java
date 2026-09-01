import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MonthSeason {

	public static String monthSeason(String string, int year) {
		string = string.toLowerCase();

		if (string.equals("march") && year >= 20 || string.equals("april")
				|| string.equals("may") || (string.equals("june") && year <= 20)) {
			return "spring";
		} else if (string.equals("june") && year >= 21 || string.equals("july")
				|| string.equals("august")
				|| (string.equals("september") && year <= 22)) {
			return "summer";
		} else if (string.equals("september") && year >= 23
				|| string.equals("october") || string.equals("november")
				|| (string.equals("december") && year <= 20)) {
			return "autumn";
		} else {
			return "winter";
		}
	}
}
