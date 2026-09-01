
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOps {

	public static int minOps(int[] arr, int n, int k) {
		int max1 = arr[0];
		int last = 1;
		while (last < n) {
			if (arr[last] > max1) {
				max1 = arr[last];
			}
			last++;
		}
		int res = 0;
		int last = 0;
		while (last < n) {
			if ((max1 - arr[last]) % k != 0) {
				return -1;
			} else {
				res += (max1 - arr[last]) / k;
			}
			last++;
		}
		return res;
	}
}
