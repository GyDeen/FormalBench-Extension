
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumOfThreeConsecutive {

	public static int maxSumOfThreeConsecutive(int[] arr, int n) {
		int[] sum = new int[n];
		if (1 <= n) {
			sum[0] = arr[0];
		}
		if (2 <= n) {
			sum[1] = sum[0] + arr[1];
		}
		if (2 < n) {
			sum[2] = Math.max(sum[1], Math.max(arr[1] + arr[2], arr[0] + arr[2]));
		}
		for (int i = 3; n > i; i++) {
			sum[i] = Math.max(Math.max(sum[i - 1], sum[i - 2] + arr[i]), arr[i] + arr[i - 1] + sum[i - 3]);
		}
		return sum[n - 1];
	}
}
