import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestRepeatingSubseq {

	public static int findLongestRepeatingSubseq(String str) {
		HashSet<Character> set = new HashSet<>();
		int max = 0;
		int n = str.length();
		int skip = 0;
		while (skip < n) {
			char ch = str.charAt(skip);
			if (set.contains(ch)) {
				max = Math.max(max, set.size());
				set.clear();
			}
			set.add(ch);
			skip++;
		}
		return max == set.size() ? 0 : max;
	}
}
