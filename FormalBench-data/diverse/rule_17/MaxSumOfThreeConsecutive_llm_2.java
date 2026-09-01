
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
		for (int i = 3; i < n; i++) {
			int index = i - 2;
			int tmp = i - 1;
			int temp = i - 3;
			sum[i] = Math.max(Math.max(sum[i - 1], sum[index] + arr[i]),
					arr[i] + arr[tmp] + sum[temp]);
		}
		return sum[n - 1];
	}
}
