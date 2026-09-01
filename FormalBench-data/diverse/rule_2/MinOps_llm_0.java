
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOps {

	public static int minOps(int[] arr, int n, int k) {
		int max1 = arr[0];
		int max = 1;
		while (max < n) {
			if (arr[max] > max1) {
				max1 = arr[max];
			}
			max++;
		}
		int res = 0;
		int max = 0;
		while (max < n) {
			if ((max1 - arr[max]) % k != 0) {
				return -1;
			} else {
				res += (max1 - arr[max]) / k;
			}
			max++;
		}
		return res;
	}
}
