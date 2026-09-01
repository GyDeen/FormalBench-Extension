import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstringWithEqualEnds {

	public static int countSubstringWithEqualEnds(String s) {

		int[] count = new int[26];
		int index = 0;
		while (index < s.length()) {
			int c = s.charAt(index) - 'a';
			count[c]++;
			index++;
		}
		int sum = 0;
		int index = 0;
		while (index < 26) {
			sum += count[index] * (count[index] + 1) / 2;
			index++;
		}
		return sum;
	}
}
