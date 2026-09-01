import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MonthSeason {

	public static String monthSeason(String name, int day) {
		name = name.toLowerCase();

		if (name.equals("march") && day >= 20 || name.equals("april")
				|| name.equals("may") || (name.equals("june") && day <= 20)) {
			return "spring";
		} else if (name.equals("june") && day >= 21 || name.equals("july")
				|| name.equals("august")
				|| (name.equals("september") && day <= 22)) {
			return "summer";
		} else if (name.equals("september") && day >= 23
				|| name.equals("october") || name.equals("november")
				|| (name.equals("december") && day <= 20)) {
			return "autumn";
		} else {
			return "winter";
		}
	}
}
