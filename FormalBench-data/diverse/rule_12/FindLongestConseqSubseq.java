
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestConseqSubseq {

	public static int findLongestConseqSubseq(int[] arr, int n) {
		if (n == 0)
			return 0;

		Arrays.sort(arr);

		int count = 1;
		int ans = 0;

		for (int i = 1; i < n; i++) {
			if (arr[i] == arr[i - 1]) {
				continue;
			}

			if (arr[i] == arr[i - 1] + 1) {
				count++;
			} else {
				ans = Math.max(ans, count);
				count = 1;
			}
		}

		ans = Math.max(ans, count);

		return ans;
	}
}
