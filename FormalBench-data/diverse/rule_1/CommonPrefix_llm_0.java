
import java.io.*;
import java.lang.*;
import java.math.*;

class CommonPrefix {

	public static String commonPrefix(String[] args, int len) {
		if (len == 0)
			return "";
		String result = args[0];
		for (int j = 1; j < len; j++) {
			while (args[j].indexOf(result) != 0) {
				result = result.substring(0, result.length() - 1);
			}
		}
		System.out.println(result);
		return result;
	}
}
