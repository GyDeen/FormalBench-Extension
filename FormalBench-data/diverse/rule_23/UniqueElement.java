
import java.io.*;
import java.lang.*;
import java.math.*;

class UniqueElement {

	public static String uniqueElement(int[] arr, int n) {
		if (n == 0) {
			return "NO";
		}

		int firstElement = arr[0];
		for (int i = 1; n > i; i++) {
			if (firstElement != arr[i]) {
				return "NO";
			}
		}
		return "YES";
	}
}
