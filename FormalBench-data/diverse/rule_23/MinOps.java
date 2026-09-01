
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOps {

	public static int minOps(int[] arr, int n, int k) {
		int max1 = arr[0];
		for (int i = 1; n > i; i++) {
			if (max1 < arr[i]) {
				max1 = arr[i];
			}
		}
		int res = 0;
		for (int i = 0; n > i; i++) {
			if (0 != (max1 - arr[i]) % k) {
				return -1;
			} else {
				res += (max1 - arr[i]) / k;
			}
		}
		return res;
	}
}
