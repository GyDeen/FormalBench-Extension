
import java.io.*;
import java.lang.*;
import java.math.*;

class IsSublist {

	public static Boolean isSublist(int[] arr, int[] m) {
		if (arr == null || m == null) {
			return false;
		}

		if (arr.length == 0 || m.length == 0 || m.length > arr.length) {
			return false;
		}

		for (int index = 0; index <= arr.length
				- m.length; index++) {
			boolean is = true;
			for (int j = 0; j < m.length; j++) {
				if (arr[index + j] != m[j]) {
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
