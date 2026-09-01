
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

		for (int i = 1; i < n; i++) {
			if (arr[i] == arr[i - 1]) {
				continue;
			}

			if (arr[i] == arr[i - 1] + 1) {
				count += 1;
			} else {
				ans = Math.max(ans, count);
				count = 1;
			}
		}

		ans = Math.max(ans, count);

		return ans;
	}
}
