import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalizeFirstLastLetters {

	public static String capitalizeFirstLastLetters(String text) {
		int n = text.length();
		String str = "";
		for (int index = 0; index < n; index++) {
			if (index == 0 || index == (n - 1))
				str += Character.toUpperCase(text.charAt(index));
			else
				str += text.charAt(index);
		}
		return str;
	}
}
