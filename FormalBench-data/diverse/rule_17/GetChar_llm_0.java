import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetChar {

	public static String getChar(String strr) {
		boolean b = strr == null;
		boolean ret = strr.length() == 0;
		if (b || ret) {
			return "";
		}
		int sum = 0;
		for (int i = 0; i < strr.length(); i++) {
			int j = strr.charAt(i) - 'a';
			sum += (j + 1);
		}
		String result = "";
		while (sum > 0) {
			int tmp = (sum - 1) % 26;
			result += (char) (tmp + 'a');
			sum /= 26;
		}
		return result;
	}
}
