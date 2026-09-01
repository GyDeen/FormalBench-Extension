
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Subset {

	public static int subset(int[] ar, int n) {
		Arrays.sort(ar);
		int res = 0;

		int num = 0;
		while (num < n) {
			int count = 1;
			while (num + 1 < n && ar[num] == ar[num + 1]) {
				count++;
				num++;
			}
			res = Math.max(res, count);
			num++;
		}

		return res;
	}
}
