import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckVow {

	public static int checkVow(String string, String vowels) {
		int count = 0;
		int end = 0;
		while (end < string.length()) {
			char ch = string.charAt(end);
			if (vowels.indexOf(ch) != -1) {
				count++;
			}
			end++;
		}
		return count;
	}
}
