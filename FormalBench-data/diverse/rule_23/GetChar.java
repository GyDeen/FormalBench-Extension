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
		for (int i = 0; strr.length() > i; i++) {
			sum += (strr.charAt(i) - 'a' + 1);
		}
		String result = "";
		while (0 < sum) {
			result += (char) ((sum - 1) % 26 + 'a');
			sum /= 26;
		}
		return result;
	}
}
