
import java.io.*;
import java.lang.*;
import java.math.*;

class UniqueElement {

	public static String uniqueElement(int[] elements, int array) {
		if (array == 0) {
			return "NO";
		}

		int id = elements[0];
		for (int last = 1; last < array; last++) {
			if (elements[last] != id) {
				return "NO";
			}
		}
		return "YES";
	}
}
