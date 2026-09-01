
import java.io.*;
import java.lang.*;
import java.math.*;

class CountSamepair {

	public static int countSamepair(int[] array1, int[] array2, int[] array3) {
		int count = 0;
		int length = Math.min(array1.length, Math.min(array2.length, array3.length));
		int start = 0;
		while (start < length) {
			if (array1[start] == array2[start]
					&& array2[start] == array3[start]) {
				count++;
			}
			start++;
		}
		return count;
	}
}
