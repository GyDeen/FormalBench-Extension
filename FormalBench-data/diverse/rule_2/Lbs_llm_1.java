
import java.io.*;
import java.lang.*;
import java.math.*;

class Lbs {

	public static int lbs(int[] arr) {
		int n = arr.length;
		int[] l = new int[n];
		int[] r = new int[n];
		int max = 0;

		int tmp = 0;
		while (tmp < n) {
			l[tmp] = 1;
			r[tmp] = 1;
			for (int j = 0; j < tmp; j++) {
				if (arr[tmp] > arr[j]) {
					l[tmp] = Math.max(l[tmp], l[j] + 1);
				}
				if (arr[tmp] < arr[j]) {
					r[tmp] = Math.max(r[tmp], r[j] + 1);
				}
			}
			max = Math.max(max, l[tmp] + r[tmp] - 1);
			tmp++;
		}
		return max;
	}
}
