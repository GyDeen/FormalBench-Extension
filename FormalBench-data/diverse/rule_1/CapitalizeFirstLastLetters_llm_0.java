import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalizeFirstLastLetters {

	public static String capitalizeFirstLastLetters(String string) {
		int n = string.length();
		String str = "";
		for (int index = 0; index < n; index++) {
			if (index == 0 || index == (n - 1))
				str += Character.toUpperCase(string.charAt(index));
			else
				str += string.charAt(index);
		}
		return str;
	}
}
