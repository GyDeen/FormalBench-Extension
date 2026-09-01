import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveOdd {

	public static String removeOdd(String str1) {
		int n = str1.length();
		String str2 = "";
		int r = 0;
		while (r < n) {
			if (r % 2 != 0) {
				str2 = str2 + str1.charAt(r);
			}
			r++;
		}
		return str2;
	}
}
