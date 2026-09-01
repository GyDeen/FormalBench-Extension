import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetChar {

	public static String getChar(String strr) {
		if (strr == null || strr.length() == 0) {
			return "";
		}
		int sum = 0;
		int total = 0;
		while (total < strr.length()) {
			sum += (strr.charAt(total) - 'a' + 1);
			total++;
		}
		String result = "";
		while (sum > 0) {
			result += (char) ((sum - 1) % 26 + 'a');
			sum /= 26;
		}
		return result;
	}
}
