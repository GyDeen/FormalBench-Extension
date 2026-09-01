import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalizeFirstLastLetters {

	public static String capitalizeFirstLastLetters(String str1) {
		int length = str1.length();
		String str2 = "";
		for (int i = 0; i < length; i++) {
			boolean result = i == 0;
			boolean temp = i == (length - 1);
			if (result || temp)
				str2 += Character.toUpperCase(str1.charAt(i));
			else
				str2 += str1.charAt(i);
		}
		return str2;
	}
}
