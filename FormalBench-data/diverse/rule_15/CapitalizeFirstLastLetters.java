import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalizeFirstLastLetters {

	public static String capitalizeFirstLastLetters(String str1) {
		int length = str1.length();
		String str2 = "";
		for (int i = 0; i < length; i++) {
			str2 = (i == 0 || i == (length - 1)) ? Character.toUpperCase(str1.charAt(i)) : str1.charAt(i);
		}
		return str2;
	}
}
