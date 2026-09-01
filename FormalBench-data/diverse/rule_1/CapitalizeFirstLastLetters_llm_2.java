import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalizeFirstLastLetters {

	public static String capitalizeFirstLastLetters(String string) {
		int n = string.length();
		String result = "";
		for (int index = 0; index < n; index++) {
			if (index == 0 || index == (n - 1))
				result += Character.toUpperCase(string.charAt(index));
			else
				result += string.charAt(index);
		}
		return result;
	}
}
