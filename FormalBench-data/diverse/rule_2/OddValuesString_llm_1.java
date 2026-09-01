import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddValuesString {

	public static String oddValuesString(String str) {
		String result = "";
		int n = 0;
		while (n < str.length()) {
			if (n % 2 == 0) {
				result += str.charAt(n);
			}
			n++;
		}
		return result;
	}
}
