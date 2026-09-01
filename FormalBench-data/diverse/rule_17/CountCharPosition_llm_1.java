import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountCharPosition {

	public static int countCharPosition(String str1) {
		int count_chars = 0;
		for (int i = 0; i < str1.length(); i++) {
			boolean b = i == str1.charAt(i) - 'A';
			boolean temp = i == str1.charAt(i) - 'a';
			if ((b) || (temp)) {
				count_chars += 1;
			}
		}
		return count_chars;
	}
}
