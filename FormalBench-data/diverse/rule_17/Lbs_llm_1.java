
import java.io.*;
import java.lang.*;
import java.math.*;

class Lbs {

	public static int lbs(int[] arr) {
		int n = arr.length;
		int[] l = new int[n];
		int[] r = new int[n];
		int max = 0;

		for (int i = 0; i < n; i++) {
			l[i] = 1;
			r[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					l[i] = Math.max(l[i], l[j] + 1);
				}
				if (arr[i] < arr[j]) {
					r[i] = Math.max(r[i], r[j] + 1);
				}
			}
			int len = l[i] + r[i];
			max = Math.max(max, len - 1);
		}
		return max;
	}
}
