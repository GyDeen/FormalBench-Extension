
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSubArray {

	public static Boolean isSubArray(int[] a, int[] b, int n, int m) {
		if (m > n) {
			return false;
		}

		int i = 0;

		while (n - m >= i) {
			int j;
			for (j = 0; m > j; j++) {
				if (b[j] != a[i + j]) {
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
