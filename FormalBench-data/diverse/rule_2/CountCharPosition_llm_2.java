import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountCharPosition {

	public static int countCharPosition(String str1) {
		int count_chars = 0;
		int total = 0;
		while (total < str1.length()) {
			if ((total == str1.charAt(total) - 'A')
					|| (total == str1.charAt(total) - 'a')) {
				count_chars += 1;
			}
			total++;
		}
		return count_chars;
	}
}
