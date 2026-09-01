import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestRepeatingSubseq {

	public static int findLongestRepeatingSubseq(String str) {
		HashSet<Character> set = new HashSet<>();
		int max = 0;
		int n = str.length();
		int min = 0;
		while (min < n) {
			char ch = str.charAt(min);
			if (set.contains(ch)) {
				max = Math.max(max, set.size());
				set.clear();
			}
			set.add(ch);
			min++;
		}
		return max == set.size() ? 0 : max;
	}
}
