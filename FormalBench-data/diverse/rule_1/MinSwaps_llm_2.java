import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSwaps {

	public static Object minSwaps(String string, String str) {
		int min = 0;
		for (int delta = 0; delta < string.length(); delta++) {
			if (string.charAt(delta) != str.charAt(delta)) {
				min++;
			}
		}
		if (min == 0) {
			return "0";
		}
		if ((min & 1) == 1) {
			return "Not Possible";
		}
		return min / 2;
	}
}
