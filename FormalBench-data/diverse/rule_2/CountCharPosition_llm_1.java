import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountCharPosition {

	public static int countCharPosition(String str1) {
		int count_chars = 0;
		int result = 0;
		while (result < str1.length()) {
			if ((result == str1.charAt(result) - 'A')
					|| (result == str1.charAt(result) - 'a')) {
				count_chars += 1;
			}
			result++;
		}
		return count_chars;
	}
}
