
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSubArray {

	public static Boolean isSubArray(int[] a, int[] b, int n, int m) {
		if (n < m) {
			return false;
		}

		int i = 0;

		while (i <= n - m) {
			int j;
			for (j = 0; j < m; j++) {
				if (a[i + j] != b[j]) {
					break;
				}
			}

			if (m == j) {
				return true;
			}

			i++;
		}

		return false;
	}
}
