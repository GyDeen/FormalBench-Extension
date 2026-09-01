import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MonthSeason {

	public static String monthSeason(String month, int days) {
		month = month.toLowerCase();

		if ("march".equals(month) && days >= 20 || "april".equals(month) || "may".equals(month)
				|| ("june".equals(month) && days <= 20)) {
			return "spring";
		} else if ("june".equals(month) && days >= 21 || "july".equals(month) || "august".equals(month)
				|| ("september".equals(month) && days <= 22)) {
			return "summer";
		} else if ("september".equals(month) && days >= 23 || "october".equals(month) || "november".equals(month)
				|| ("december".equals(month) && days <= 20)) {
			return "autumn";
		} else {
			return "winter";
		}
	}
}
