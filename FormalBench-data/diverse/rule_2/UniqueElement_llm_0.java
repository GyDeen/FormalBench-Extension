
import java.io.*;
import java.lang.*;
import java.math.*;

class UniqueElement {

	public static String uniqueElement(int[] arr, int n) {
		if (n == 0) {
			return "NO";
		}

		int firstElement = arr[0];
		int last = 1;
		while (last < n) {
			if (arr[last] != firstElement) {
				return "NO";
			}
			last++;
		}
		return "YES";
	}
}
