
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

		int seq = 1;
		while (seq < n) {
			if (arr[seq] == arr[seq - 1]) {
				continue;
			}
			if (arr[seq] == arr[seq - 1] + 1) {
				count++;
			} else {
				ans = Math.max(ans, count);
				count = 1;
			}
			seq++;
		}

		ans = Math.max(ans, count);

		return ans;
	}
}
