import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinSwaps {

	public static Object minSwaps(String str1, String str2) {
		int diff = 0;
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				diff++;
			}
		}
		if (0 == diff) {
			return "0";
		}
		if (1 == (diff & 1)) {
			return "Not Possible";
		}
		return diff / 2;
	}
}
