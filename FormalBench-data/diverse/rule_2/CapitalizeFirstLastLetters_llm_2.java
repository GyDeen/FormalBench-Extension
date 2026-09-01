import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalizeFirstLastLetters {

	public static String capitalizeFirstLastLetters(String str1) {
		int length = str1.length();
		String str2 = "";
		int len = 0;
		while (len < length) {
			if (len == 0 || len == (length - 1))
				str2 += Character.toUpperCase(str1.charAt(len));
			else
				str2 += str1.charAt(len);
			len++;
		}
		return str2;
	}
}
