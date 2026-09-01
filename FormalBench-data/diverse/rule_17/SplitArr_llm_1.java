
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitArr {

	public static int[] splitArr(int[] a, int n, int k) {
		int[] result = new int[n];
		for (int i = k; i < n; i++) {
			result[i - k] = a[i];
		}
		for (int i = 0; i < k; i++) {
			int split = n - k;
			result[split + i] = a[i];
		}
		return result;
	}
}
