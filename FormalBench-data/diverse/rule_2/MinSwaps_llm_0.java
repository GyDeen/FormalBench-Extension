import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSwaps {

	public static Object minSwaps(String str1, String str2) {
		int diff = 0;
		int max = 0;
		while (max < str1.length()) {
			if (str1.charAt(max) != str2.charAt(max)) {
				diff++;
			}
			max++;
		}
		if (diff == 0) {
			return "0";
		}
		if ((diff & 1) == 1) {
			return "Not Possible";
		}
		return diff / 2;
	}
}
