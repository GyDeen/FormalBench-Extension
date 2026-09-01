
import java.io.*;
import java.lang.*;
import java.math.*;

class Lbs {

	public static int lbs(int[] arr) {
		int n = arr.length;
		int[] p = new int[n];
		int[] s = new int[n];
		int b = 0;

		for (int len = 0; len < n; len++) {
			p[len] = 1;
			s[len] = 1;
			for (int c = 0; c < len; c++) {
				if (arr[len] > arr[c]) {
					p[len] = Math.max(p[len],
							p[c] + 1);
				}
				if (arr[len] < arr[c]) {
					s[len] = Math.max(s[len],
							s[c] + 1);
				}
			}
			b = Math.max(b,
					p[len] + s[len] - 1);
		}
		return b;
	}
}
