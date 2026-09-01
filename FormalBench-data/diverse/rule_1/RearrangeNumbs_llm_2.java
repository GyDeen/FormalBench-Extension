
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RearrangeNumbs {

	public static int[] rearrangeNumbs(int[] arrayNums) {
		int posCount = 0;
		int count = 0;

		for (int i : arrayNums) {
			if (i > 0) {
				posCount++;
			} else if (i < 0) {
				count++;
			}
		}

		int[] array = new int[posCount];
		int[] neg = new int[count];
		posCount = 0;
		count = 0;

		for (int i : arrayNums) {
			if (i > 0) {
				array[posCount++] = i;
			} else if (i < 0) {
				neg[count++] = i;
			}
		}

		Arrays.sort(array);
		Arrays.sort(neg);

		int[] result = new int[array.length + neg.length];
		System.arraycopy(array, 0, result, 0, array.length);
		System.arraycopy(neg, 0, result, array.length, neg.length);

		return result;
	}
}
