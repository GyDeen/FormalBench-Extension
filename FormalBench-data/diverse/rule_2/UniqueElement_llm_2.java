
import java.io.*;
import java.lang.*;
import java.math.*;

class UniqueElement {

	public static String uniqueElement(int[] arr, int n) {
		if (n == 0) {
			return "NO";
		}

		int firstElement = arr[0];
		int rest = 1;
		while (rest < n) {
			if (arr[rest] != firstElement) {
				return "NO";
			}
			rest++;
		}
		return "YES";
	}
}
