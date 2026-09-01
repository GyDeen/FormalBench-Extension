
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSubArray {

	public static Boolean isSubArray(int[] a, int[] b, int n, int m) {
		int i = 0;

		if (n < m) {
			return false;
		}

		while (i <= n - m) {
			int j;
			for (j = 0; j < m; j++) {
				if (a[i + j] != b[j]) {
					break;
				}
			}

			i++;

			if (j == m) {
				return true;
			}
		}

		return false;
	}
}
