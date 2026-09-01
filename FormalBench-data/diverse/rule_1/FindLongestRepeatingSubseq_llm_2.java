import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestRepeatingSubseq {

	public static int findLongestRepeatingSubseq(String string) {
		HashSet<Character> chars = new HashSet<>();
		int found = 0;
		int skip = string.length();
		for (int i = 0; i < skip; i++) {
			char c = string.charAt(i);
			if (chars.contains(c)) {
				found = Math.max(found, chars.size());
				chars.clear();
			}
			chars.add(c);
		}
		return found == chars.size() ? 0 : found;
	}
}
