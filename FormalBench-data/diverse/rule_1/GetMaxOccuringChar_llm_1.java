import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxOccuringChar {

	public static String getMaxOccuringChar(String str1) {
		int ret = 0;
		String str = "";
		for (int index = 0; index < str1.length(); index++) {
			int cur = 0;
			for (int c = 0; c < str1.length(); c++) {
				if (str1.charAt(index) == str1.charAt(c)) {
					cur++;
				}
			}
			if (cur > ret) {
				ret = cur;
				str = str1.charAt(index) + "";
			}
		}
		return str;
	}
}
