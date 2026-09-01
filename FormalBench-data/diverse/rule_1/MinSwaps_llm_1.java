import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSwaps {

	public static Object minSwaps(String string, String str) {
		int result = 0;
		for (int difference = 0; difference < string.length(); difference++) {
			if (string.charAt(difference) != str.charAt(difference)) {
				result++;
			}
		}
		if (result == 0) {
			return "0";
		}
		if ((result & 1) == 1) {
			return "Not Possible";
		}
		return result / 2;
	}
}
