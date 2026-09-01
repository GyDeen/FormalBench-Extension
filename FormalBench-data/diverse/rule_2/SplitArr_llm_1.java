
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitArr {

	public static int[] splitArr(int[] a, int n, int k) {
		int[] result = new int[n];
		int arr = k;
		while (arr < n) {
			result[arr - k] = a[arr];
			arr++;
		}
		int arr = 0;
		while (arr < k) {
			result[n - k + arr] = a[arr];
			arr++;
		}
		return result;
	}
}
