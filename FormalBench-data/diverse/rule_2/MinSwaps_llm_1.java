import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSwaps {

	public static Object minSwaps(String str1, String str2) {
		int diff = 0;
		int result = 0;
		while (result < str1.length()) {
			if (str1.charAt(result) != str2.charAt(result)) {
				diff++;
			}
			result++;
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
