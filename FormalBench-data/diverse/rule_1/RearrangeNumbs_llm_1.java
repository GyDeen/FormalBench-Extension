
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RearrangeNumbs {

	public static int[] rearrangeNumbs(int[] arrayNums) {
		int posCount = 0;
		int max = 0;

		for (int i : arrayNums) {
			if (i > 0) {
				posCount++;
			} else if (i < 0) {
				max++;
			}
		}

		int[] array = new int[posCount];
		int[] dest = new int[max];
		posCount = 0;
		max = 0;

		for (int i : arrayNums) {
			if (i > 0) {
				array[posCount++] = i;
			} else if (i < 0) {
				dest[max++] = i;
			}
		}

		Arrays.sort(array);
		Arrays.sort(dest);

		int[] result = new int[array.length + dest.length];
		System.arraycopy(array, 0, result, 0, array.length);
		System.arraycopy(dest, 0, result, array.length, dest.length);

		return result;
	}
}
