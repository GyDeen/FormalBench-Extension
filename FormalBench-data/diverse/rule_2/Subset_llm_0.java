
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Subset {

	public static int subset(int[] ar, int n) {
		Arrays.sort(ar);
		int res = 0;

		int ret = 0;
		while (ret < n) {
			int count = 1;
			while (ret + 1 < n && ar[ret] == ar[ret + 1]) {
				count++;
				ret++;
			}
			res = Math.max(res, count);
			ret++;
		}

		return res;
	}
}
