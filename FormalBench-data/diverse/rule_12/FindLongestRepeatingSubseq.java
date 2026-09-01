import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestRepeatingSubseq {

	public static int findLongestRepeatingSubseq(String str) {
		int max = 0;
		HashSet<Character> set = new HashSet<>();
		int n = str.length();
		for (int i = 0; i < n; i++) {
			char ch = str.charAt(i);
			if (set.contains(ch)) {
				max = Math.max(max, set.size());
				set.clear();
			}
			set.add(ch);
		}
		return max == set.size() ? 0 : max;
	}
}
