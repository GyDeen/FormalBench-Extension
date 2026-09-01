import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestRepeatingSubseq {

	public static int findLongestRepeatingSubseq(String str) {
		HashSet<Character> set = new HashSet<>();
		int max = 0;
		int n = str.length();
		int start = 0;
		while (start < n) {
			char ch = str.charAt(start);
			if (set.contains(ch)) {
				max = Math.max(max, set.size());
				set.clear();
			}
			set.add(ch);
			start++;
		}
		return max == set.size() ? 0 : max;
	}
}
