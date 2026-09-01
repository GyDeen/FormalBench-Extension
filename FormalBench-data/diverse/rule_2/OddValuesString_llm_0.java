import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddValuesString {

	public static String oddValuesString(String str) {
		String result = "";
		int i = 0;
		while (i < str.length()) {
			if (i % 2 == 0) {
				result += str.charAt(i);
			}
			i++;
		}
		return result;
	}
}
