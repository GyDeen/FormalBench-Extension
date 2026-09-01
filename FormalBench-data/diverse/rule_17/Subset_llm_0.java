
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Subset {

	public static int subset(int[] ar, int n) {
		Arrays.sort(ar);
		int res = 0;

		for (int i = 0; i < n;) {
			int count = 1;
			boolean b = i + 1 < n;
			boolean tmp = ar[i] == ar[i + 1];
			while (b && tmp) {
				count++;
				i++;
			}
			res = Math.max(res, count);
			i++;
		}

		return res;
	}
}
