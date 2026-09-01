
import java.io.*;
import java.lang.*;
import java.math.*;

class CountSamepair {

	public static int countSamepair(int[] array1, int[] array2, int[] array3) {
		int count = 0;
		int length = Math.min(array1.length, Math.min(array2.length, array3.length));
		for (int i = 0; i < length; i++) {
			if (array1[i] == array2[i]) {
				if (array2[i] == array3[i]) {
					count++;
				}
			}
		}
		return count;
	}
}
