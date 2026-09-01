import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSubstringWithEqualEnds {

	public static int countSubstringWithEqualEnds(String s) {

		int[] count = new int[26];
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i) - 'a';
			count[c]++;
		}
		int sum = 0;
		for (int i = 0; i < 26; i++) {
			int a = count[i] * (count[i] + 1);
			sum += a / 2;
		}
		return sum;
	}
}
