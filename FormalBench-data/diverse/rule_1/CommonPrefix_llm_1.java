
import java.io.*;
import java.lang.*;
import java.math.*;

class CommonPrefix {

	public static String commonPrefix(String[] args, int len) {
		if (len == 0)
			return "";
		String str = args[0];
		for (int j = 1; j < len; j++) {
			while (args[j].indexOf(str) != 0) {
				str = str.substring(0, str.length() - 1);
			}
		}
		System.out.println(str);
		return str;
	}
}
