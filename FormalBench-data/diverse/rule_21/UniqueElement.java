
import java.io.*;
import java.lang.*;
import java.math.*;

class UniqueElement {

	public static String uniqueElement(int[] arr, int n) {
		if (0 == n) {
			return "NO";
		}

		int firstElement = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] != firstElement) {
				return "NO";
			}
		}
		return "YES";
	}
}
