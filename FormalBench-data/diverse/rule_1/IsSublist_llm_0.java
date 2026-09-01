
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSublist {

	public static Boolean isSublist(int[] arr, int[] r) {
		if (arr == null || r == null) {
			return false;
		}

		if (arr.length == 0 || r.length == 0 || r.length > arr.length) {
			return false;
		}

		for (int index = 0; index <= arr.length
				- r.length; index++) {
			boolean is = true;
			for (int j = 0; j < r.length; j++) {
				if (arr[index + j] != r[j]) {
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
