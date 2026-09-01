
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestConseqSubseq {

	public static int findLongestConseqSubseq(int[] arr, int n) {
		if (n == 0)
			return 0;

		Arrays.sort(arr);

		int ans = 0;
		int count = 1;

		int end = 1;
		while (end < n) {
			if (arr[end] == arr[end - 1]) {
				continue;
			}
			if (arr[end] == arr[end - 1] + 1) {
				count++;
			} else {
				ans = Math.max(ans, count);
				count = 1;
			}
			end++;
		}

		ans = Math.max(ans, count);

		return ans;
	}
}
