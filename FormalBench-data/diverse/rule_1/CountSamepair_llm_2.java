
import java.io.*;
import java.lang.*;
import java.math.*;

class CountSamepair {

	public static int countSamepair(int[] array1, int[] array, int[] arrays) {
		int ret = 0;
		int result = Math.min(array1.length,
				Math.min(array.length, arrays.length));
		for (int index = 0; index < result; index++) {
			if (array1[index] == array[index]
					&& array[index] == arrays[index]) {
				ret++;
			}
		}
		return ret;
	}
}
