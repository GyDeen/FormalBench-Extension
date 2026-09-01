
import java.io.*;
import java.lang.*;
import java.math.*;

class CountSamepair {

	public static int countSamepair(int[] array1, int[] element, int[] array) {
		int result = 0;
		int total = Math.min(array1.length,
				Math.min(element.length, array.length));
		for (int index = 0; index < total; index++) {
			if (array1[index] == element[index]
					&& element[index] == array[index]) {
				result++;
			}
		}
		return result;
	}
}
