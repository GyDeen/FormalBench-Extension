import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetChar {

	public static String getChar(String strr) {
		if (null == strr || 0 == strr.length()) {
			return "";
		}
		int sum = 0;
		for (int i = 0; i < strr.length(); i++) {
			sum += (strr.charAt(i) - 'a' + 1);
		}
		String result = "";
		while (sum > 0) {
			result += (char) ((sum - 1) % 26 + 'a');
			sum /= 26;
		}
		return result;
	}
}
