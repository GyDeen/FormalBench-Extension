
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOps {

	public static int minOps(int[] arr, int n, int k) {
		int max1 = arr[0];
		int res = 0;
		for (int i = 1; i < n; i++) {
			if (arr[i] > max1) {
				max1 = arr[i];
			}
		}
		for (int i = 0; i < n; i++) {
			if ((max1 - arr[i]) % k != 0) {
				return -1;
			} else {
				res += (max1 - arr[i]) / k;
			}
		}
		return res;
	}
}
