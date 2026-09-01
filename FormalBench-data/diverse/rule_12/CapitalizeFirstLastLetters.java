import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalizeFirstLastLetters {

	public static String capitalizeFirstLastLetters(String str1) {
		String str2 = "";
		int length = str1.length();
		for (int i = 0; i < length; i++) {
			if (i == 0 || i == (length - 1))
				str2 += Character.toUpperCase(str1.charAt(i));
			else
				str2 += str1.charAt(i);
		}
		return str2;
	}
}
