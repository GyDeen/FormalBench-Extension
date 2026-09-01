import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetChar {

	public static String getChar(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		int n = 0;
		for (int count = 0; count < str.length(); count++) {
			n += (str.charAt(count) - 'a' + 1);
		}
		String get = "";
		while (n > 0) {
			get += (char) ((n - 1) % 26 + 'a');
			n /= 26;
		}
		return get;
	}
}
