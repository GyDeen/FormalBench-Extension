import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxOccuringChar {

	public static String getMaxOccuringChar(String str1) {
		int ret = 0;
		String str = "";
		for (int c = 0; c < str1.length(); c++) {
			int cur = 0;
			for (int n = 0; n < str1.length(); n++) {
				if (str1.charAt(c) == str1.charAt(n)) {
					cur++;
				}
			}
			if (cur > ret) {
				ret = cur;
				str = str1.charAt(c) + "";
			}
		}
		return str;
	}
}
