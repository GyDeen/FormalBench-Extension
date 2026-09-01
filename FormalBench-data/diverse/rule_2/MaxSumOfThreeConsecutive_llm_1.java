
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
		int count = 3;
		while (count < n) {
			sum[count] = Math.max(
					Math.max(sum[count - 1], sum[count - 2] + arr[count]),
					arr[count] + arr[count - 1] + sum[count - 3]);
			count++;
		}
		return sum[n - 1];
	}
}
