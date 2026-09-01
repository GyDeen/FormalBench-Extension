import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountCharPosition {

	public static int countCharPosition(String str1) {
		int count_chars = 0;
		int count = 0;
		while (count < str1.length()) {
			if ((count == str1.charAt(count) - 'A')
					|| (count == str1.charAt(count) - 'a')) {
				count_chars += 1;
			}
			count++;
		}
		return count_chars;
	}
}
