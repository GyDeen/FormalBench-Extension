
import java.io.*;
import java.lang.*;
import java.math.*;

class UniqueElement {

	public static String uniqueElement(int[] arr, int n) {
		if (n == 0) {
			return "NO";
		}

		int firstElement = arr[0];
		int second = 1;
		while (second < n) {
			if (arr[second] != firstElement) {
				return "NO";
			}
			second++;
		}
		return "YES";
	}
}
