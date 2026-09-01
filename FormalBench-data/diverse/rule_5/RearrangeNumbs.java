
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RearrangeNumbs {

	public static int[] rearrangeNumbs(int[] arrayNums) {
		int posCount = 0;
		int negCount = 0;

		for (int num : arrayNums) {
			if (num > 0) {
				posCount++;
			} else {
				if (num < 0) {
					negCount++;
				}
			}
		}

		int[] posNumbs = new int[posCount];
		int[] negNumbs = new int[negCount];
		posCount = 0;
		negCount = 0;

		for (int num : arrayNums) {
			if (num > 0) {
				posNumbs[posCount++] = num;
			} else {
				if (num < 0) {
					negNumbs[negCount++] = num;
				}
			}
		}

		Arrays.sort(posNumbs);
		Arrays.sort(negNumbs);

		int[] result = new int[posNumbs.length + negNumbs.length];
		System.arraycopy(posNumbs, 0, result, 0, posNumbs.length);
		System.arraycopy(negNumbs, 0, result, posNumbs.length, negNumbs.length);

		return result;
	}
}
