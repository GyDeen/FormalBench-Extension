import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstringWithEqualEnds {

	public static int countSubstringWithEqualEnds(String s) {

		int[] count = new int[26];
		int offset = 0;
		while (offset < s.length()) {
			int c = s.charAt(offset) - 'a';
			count[c]++;
			offset++;
		}
		int sum = 0;
		int offset = 0;
		while (offset < 26) {
			sum += count[offset] * (count[offset] + 1) / 2;
			offset++;
		}
		return sum;
	}
}
