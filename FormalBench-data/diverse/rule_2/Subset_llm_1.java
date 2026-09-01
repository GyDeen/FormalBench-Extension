
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Subset {

	public static int subset(int[] ar, int n) {
		Arrays.sort(ar);
		int res = 0;

		int r = 0;
		while (r < n) {
			int count = 1;
			while (r + 1 < n && ar[r] == ar[r + 1]) {
				count++;
				r++;
			}
			res = Math.max(res, count);
			r++;
		}

		return res;
	}
}
