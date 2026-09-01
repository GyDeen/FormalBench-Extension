
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOps {

	public static int minOps(int[] arr, int n, int k) {
		int max1 = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] > max1) {
				max1 = arr[i];
			}
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			int r = (max1 - arr[i]) % k;
			if (r != 0) {
				return -1;
			} else {
				int tmp = max1 - arr[i];
				res += (tmp) / k;
			}
		}
		return res;
	}
}
