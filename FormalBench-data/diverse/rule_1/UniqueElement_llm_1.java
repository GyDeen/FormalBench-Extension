
import java.io.*;
import java.lang.*;
import java.math.*;

class UniqueElement {

	public static String uniqueElement(int[] elements, int array) {
		if (array == 0) {
			return "NO";
		}

		int index = elements[0];
		for (int next = 1; next < array; next++) {
			if (elements[next] != index) {
				return "NO";
			}
		}
		return "YES";
	}
}
