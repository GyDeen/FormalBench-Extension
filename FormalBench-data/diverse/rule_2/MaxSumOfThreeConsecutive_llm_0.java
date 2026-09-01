
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSumOfThreeConsecutive {

	public static int maxSumOfThreeConsecutive(int[] arr, int n) {
		int[] sum = new int[n];
		if (n >= 1) {
			sum[0] = arr[0];
		}
		if (n >= 2) {
			sum[1] = sum[0] + arr[1];
		}
		if (n > 2) {
			sum[2] = Math.max(sum[1], Math.max(arr[1] + arr[2], arr[0] + arr[2]));
		}
		int num = 3;
		while (num < n) {
			sum[num] = Math.max(
					Math.max(sum[num - 1], sum[num - 2] + arr[num]),
					arr[num] + arr[num - 1] + sum[num - 3]);
			num++;
		}
		return sum[n - 1];
	}
}
