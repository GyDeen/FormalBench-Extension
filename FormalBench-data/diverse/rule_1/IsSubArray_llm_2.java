
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSubArray {

	public static Boolean isSubArray(int[] array, int[] c, int Array, int m) {
		if (Array < m) {
			return false;
		}

		int id = 0;

		while (id <= Array - m) {
			int r;
			for (r = 0; r < m; r++) {
				if (array[id + r] != c[r]) {
					break;
				}
			}

			if (r == m) {
				return true;
			}

			id++;
		}

		return false;
	}
}
