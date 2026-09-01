
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSubArray {

	public static Boolean isSubArray(int[] a, int[] b, int n, int m) {
		if (!(n < m))
			;
		else {
			return false;
		}

		int i = 0;

		while (i <= n - m) {
			int j;
			for (j = 0; j < m; j++) {
				if (!(a[i + j] != b[j]))
					;
				else {
					break;
				}
			}

			if (!(j == m))
				;
			else {
				return true;
			}

			i++;
		}

		return false;
	}
}
