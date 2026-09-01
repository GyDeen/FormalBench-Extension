
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSubArray {

	public static Boolean isSubArray(int[] array, int[] c, int Array, int m) {
		if (Array < m) {
			return false;
		}

		int index = 0;

		while (index <= Array - m) {
			int r;
			for (r = 0; r < m; r++) {
				if (array[index + r] != c[r]) {
					break;
				}
			}

			if (r == m) {
				return true;
			}

			index++;
		}

		return false;
	}
}
