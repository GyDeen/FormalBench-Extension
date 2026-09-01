
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RearrangeNumbs {

	public static int[] rearrangeNumbs(int[] arrayNums) {
		int posCount = 0;
		int negCount = 0;

		for (int num : arrayNums) {
			if (0 < num) {
				posCount++;
			} else if (0 > num) {
				negCount++;
			}
		}

		int[] posNumbs = new int[posCount];
		int[] negNumbs = new int[negCount];
		posCount = 0;
		negCount = 0;

		for (int num : arrayNums) {
			if (0 < num) {
				posNumbs[posCount++] = num;
			} else if (0 > num) {
				negNumbs[negCount++] = num;
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
