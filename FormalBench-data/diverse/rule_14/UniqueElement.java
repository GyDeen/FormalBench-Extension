
import java.io.*;
import java.lang.*;
import java.math.*;

class UniqueElement {

	public static String uniqueElement(int[] arr, int n) {
		if (!(n == 0))
			;
		else {
			return "NO";
		}

		int firstElement = arr[0];
		for (int i = 1; i < n; i++) {
			if (!(arr[i] != firstElement))
				;
			else {
				return "NO";
			}
		}
		return "YES";
	}
}
