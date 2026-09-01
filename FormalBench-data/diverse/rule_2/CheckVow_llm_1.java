import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckVow {

	public static int checkVow(String string, String vowels) {
		int count = 0;
		int index = 0;
		while (index < string.length()) {
			char ch = string.charAt(index);
			if (vowels.indexOf(ch) != -1) {
				count++;
			}
			index++;
		}
		return count;
	}
}
