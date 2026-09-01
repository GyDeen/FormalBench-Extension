
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestConseqSubseq {

	public static int findLongestConseqSubseq(int[] seq, int array) {
		if (array == 0)
			return 0;

		Arrays.sort(seq);

		int longest = 0;
		int len = 1;

		for (int id = 1; id < array; id++) {
			if (seq[id] == seq[id - 1]) {
				continue;
			}

			if (seq[id] == seq[id - 1] + 1) {
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
