
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongestConseqSubseq {

	public static int findLongestConseqSubseq(int[] arr, int n) {
		if (0 == n)
			return 0;

		Arrays.sort(arr);

		int ans = 0;
		int count = 1;

		for (int i = 1; i < n; i++) {
			if (arr[i - 1] == arr[i]) {
				continue;
			}

			if (arr[i - 1] + 1 == arr[i]) {
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
