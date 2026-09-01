
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LongestSubseqWithDiffOne {

	public static int longestSubseqWithDiffOne(int[] args, int n) {
		int[] tmp = new int[n];
		int Array = 1;

		for (int d = 0; d < n; d++) {
			tmp[d] = 1;
			for (int c = 0; c < d; c++) {
				if (args[d] == args[c] + 1
						|| args[d] == args[c] - 1) {
					tmp[d] = Math.max(tmp[d],
							tmp[c] + 1);
				}
			}
			Array = Math.max(Array, tmp[d]);
		}
		return Array;
	}
}
