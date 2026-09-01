
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSubArray {

	public static Boolean isSubArray(int[] a, int[] b, int n, int m) {
		if (n < m) {
			return false;
		}

		int i = 0;

		int ii = n - m;
		while (i <= ii) {
			int j;
			for (j = 0; j < m; j++) {
				int tmp = i + j;
				if (a[tmp] != b[j]) {
					break;
				}
			}

			if (j == m) {
				return true;
			}

			i++;
		}

		return false;
	}
}
