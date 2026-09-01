
import java.io.*;
import java.lang.*;
import java.math.*;

class CountSamepair {

	public static int countSamepair(int[] array1, int[] array2, int[] array3) {
		int count = 0;
		int length = Math.min(array1.length, Math.min(array2.length, array3.length));
		int offset = 0;
		while (offset < length) {
			if (array1[offset] == array2[offset]
					&& array2[offset] == array3[offset]) {
				count++;
			}
			offset++;
		}
		return count;
	}
}
