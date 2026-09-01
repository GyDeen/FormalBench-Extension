
import java.io.*;
import java.lang.*;
import java.math.*;

class Lbs {

	public static int lbs(int[] arr) {
		int n = arr.length;
		int[] l = new int[n];
		int[] r = new int[n];
		int max = 0;

		int temp = 0;
		while (temp < n) {
			l[temp] = 1;
			r[temp] = 1;
			for (int j = 0; j < temp; j++) {
				if (arr[temp] > arr[j]) {
					l[temp] = Math.max(l[temp], l[j] + 1);
				}
				if (arr[temp] < arr[j]) {
					r[temp] = Math.max(r[temp], r[j] + 1);
				}
			}
			max = Math.max(max, l[temp] + r[temp] - 1);
			temp++;
		}
		return max;
	}
}
