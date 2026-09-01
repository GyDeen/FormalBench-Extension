import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOccurance {

	public static int countOccurance(String s) {
		int count = 0;
		int total = 0;
		while (total < s.length()) {
			if (s.charAt(total) == 's' && s.charAt(total + 1) == 't'
					&& s.charAt(total + 2) == 'd') {
				count++;
			}
			total++;
		}
		return count;
	}
}
