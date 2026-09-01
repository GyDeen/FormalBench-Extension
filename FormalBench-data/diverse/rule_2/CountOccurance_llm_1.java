import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOccurance {

	public static int countOccurance(String s) {
		int count = 0;
		int end = 0;
		while (end < s.length()) {
			if (s.charAt(end) == 's' && s.charAt(end + 1) == 't'
					&& s.charAt(end + 2) == 'd') {
				count++;
			}
			end++;
		}
		return count;
	}
}
