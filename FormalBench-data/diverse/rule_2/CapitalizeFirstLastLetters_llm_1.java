import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalizeFirstLastLetters {

	public static String capitalizeFirstLastLetters(String str1) {
		int length = str1.length();
		String str2 = "";
		int result = 0;
		while (result < length) {
			if (result == 0 || result == (length - 1))
				str2 += Character.toUpperCase(str1.charAt(result));
			else
				str2 += str1.charAt(result);
			result++;
		}
		return str2;
	}
}
