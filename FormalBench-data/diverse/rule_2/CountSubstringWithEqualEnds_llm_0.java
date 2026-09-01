import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstringWithEqualEnds {

	public static int countSubstringWithEqualEnds(String s) {

		int[] count = new int[26];
		int result = 0;
		while (result < s.length()) {
			int c = s.charAt(result) - 'a';
			count[c]++;
			result++;
		}
		int sum = 0;
		int result = 0;
		while (result < 26) {
			sum += count[result] * (count[result] + 1) / 2;
			result++;
		}
		return sum;
	}
}
