
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSublist {

	public static Boolean isSublist(int[] array, int[] r) {
		if (array == null || r == null) {
			return false;
		}

		if (array.length == 0 || r.length == 0 || r.length > array.length) {
			return false;
		}

		for (int index = 0; index <= array.length
				- r.length; index++) {
			boolean is = true;
			for (int j = 0; j < r.length; j++) {
				if (array[index + j] != r[j]) {
					is = false;
					break;
				}
			}
			if (is) {
				return true;
			}
		}
		return false;
	}
}
