import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckVow {

	public static int checkVow(String string, String vowels) {
		int count = 0;
		int total = 0;
		while (total < string.length()) {
			char ch = string.charAt(total);
			if (vowels.indexOf(ch) != -1) {
				count++;
			}
			total++;
		}
		return count;
	}
}
