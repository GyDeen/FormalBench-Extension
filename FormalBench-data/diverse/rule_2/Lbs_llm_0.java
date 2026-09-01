
import java.io.*;
import java.lang.*;
import java.math.*;

class Lbs {

	public static int lbs(int[] arr) {
		int n = arr.length;
		int[] l = new int[n];
		int[] r = new int[n];
		int max = 0;

		int min = 0;
		while (min < n) {
			l[min] = 1;
			r[min] = 1;
			for (int j = 0; j < min; j++) {
				if (arr[min] > arr[j]) {
					l[min] = Math.max(l[min], l[j] + 1);
				}
				if (arr[min] < arr[j]) {
					r[min] = Math.max(r[min], r[j] + 1);
				}
			}
			max = Math.max(max, l[min] + r[min] - 1);
			min++;
		}
		return max;
	}
}
