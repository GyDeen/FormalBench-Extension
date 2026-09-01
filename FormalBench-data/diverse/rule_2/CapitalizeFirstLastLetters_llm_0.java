import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalizeFirstLastLetters {

	public static String capitalizeFirstLastLetters(String str1) {
		int length = str1.length();
		String str2 = "";
		int index = 0;
		while (index < length) {
			if (index == 0 || index == (length - 1))
				str2 += Character.toUpperCase(str1.charAt(index));
			else
				str2 += str1.charAt(index);
			index++;
		}
		return str2;
	}
}
