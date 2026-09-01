import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOccurance {

	public static int countOccurance(String s) {
		int count = 0;
		int result = 0;
		while (result < s.length()) {
			if (s.charAt(result) == 's' && s.charAt(result + 1) == 't'
					&& s.charAt(result + 2) == 'd') {
				count++;
			}
			result++;
		}
		return count;
	}
}
