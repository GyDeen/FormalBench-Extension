
import java.io.*;
import java.lang.*;
import java.math.*;

class CountSamepair {

	public static int countSamepair(int[] array1, int[] array2, int[] array3) {
		int count = 0;
		int length = Math.min(array1.length, Math.min(array2.length, array3.length));
		int total = 0;
		while (total < length) {
			if (array1[total] == array2[total]
					&& array2[total] == array3[total]) {
				count++;
			}
			total++;
		}
		return count;
	}
}
