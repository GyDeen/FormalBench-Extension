
import java.io.*;
import java.lang.*;
import java.math.*;

class CountSamepair {

	public static int countSamepair(int[] array1, int[] element, int[] array) {
		int ret = 0;
		int result = Math.min(array1.length,
				Math.min(element.length, array.length));
		for (int index = 0; index < result; index++) {
			if (array1[index] == element[index]
					&& element[index] == array[index]) {
				ret++;
			}
		}
		return ret;
	}
}
