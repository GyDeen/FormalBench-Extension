
import java.io.*;
import java.lang.*;
import java.math.*;

class UniqueElement {

	public static String uniqueElement(int[] arr, int n) {
		int firstElement = arr[0];

		if (n == 0) {
			return "NO";
		}
		for (int i = 1; i < n; i++) {
			if (arr[i] != firstElement) {
				return "NO";
			}
		}
		return "YES";
	}
}
