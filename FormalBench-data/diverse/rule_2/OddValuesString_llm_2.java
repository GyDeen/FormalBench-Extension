import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddValuesString {

	public static String oddValuesString(String str) {
		String result = "";
		int count = 0;
		while (count < str.length()) {
			if (count % 2 == 0) {
				result += str.charAt(count);
			}
			count++;
		}
		return result;
	}
}
