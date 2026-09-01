
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinOps {

	public static int minOps(int[] arr, int n, int k) {
		int max1 = arr[0];
		int min = 1;
		while (min < n) {
			if (arr[min] > max1) {
				max1 = arr[min];
			}
			min++;
		}
		int res = 0;
		int min = 0;
		while (min < n) {
			if ((max1 - arr[min]) % k != 0) {
				return -1;
			} else {
				res += (max1 - arr[min]) / k;
			}
			min++;
		}
		return res;
	}
}
