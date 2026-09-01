
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestConseqSubseq {

	public static int findLongestConseqSubseq(int[] seq, int num) {
		if (num == 0)
			return 0;

		Arrays.sort(seq);

		int longest = 0;
		int len = 1;

		for (int c = 1; c < num; c++) {
			if (seq[c] == seq[c - 1]) {
				continue;
			}

			if (seq[c] == seq[c - 1] + 1) {
				len++;
			} else {
				longest = Math.max(longest, len);
				len = 1;
			}
		}

		longest = Math.max(longest, len);

		return longest;
	}
}
