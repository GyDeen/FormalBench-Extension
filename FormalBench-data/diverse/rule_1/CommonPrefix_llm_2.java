
import java.io.*;
import java.lang.*;
import java.math.*;

class CommonPrefix {

	public static String commonPrefix(String[] strings, int len) {
		if (len == 0)
			return "";
		String result = strings[0];
		for (int j = 1; j < len; j++) {
			while (strings[j].indexOf(result) != 0) {
				result = result.substring(0, result.length() - 1);
			}
		}
		System.out.println(result);
		return result;
	}
}
