
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Subset {

	public static int subset(int[] ar, int n) {
		Arrays.sort(ar);
		int res = 0;

		for (int i = 0; n > i;) {
			int count = 1;
			while (n > i + 1 && ar[i] == ar[i + 1]) {
				count++;
				i++;
			}
			res = Math.max(res, count);
			i++;
		}

		return res;
	}
}
