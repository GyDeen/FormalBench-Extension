
import java.io.*;
import java.lang.*;
import java.math.*;

class Lbs {

	public static int lbs(int[] arr) {
		int n = arr.length;
		int[] l = new int[n];
		int[] r = new int[n];
		int max = 0;

		for (int i = 0; n > i; i++) {
			l[i] = 1;
			r[i] = 1;
			for (int j = 0; i > j; j++) {
				if (arr[j] < arr[i]) {
					l[i] = Math.max(l[i], l[j] + 1);
				}
				if (arr[j] > arr[i]) {
					r[i] = Math.max(r[i], r[j] + 1);
				}
			}
			max = Math.max(max, l[i] + r[i] - 1);
		}
		return max;
	}
}
