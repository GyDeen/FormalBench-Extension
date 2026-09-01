
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSimilarIndices {

	public static int[][] maxSimilarIndices(int[][] testArray1, int[][] testArray2) {
		HashMap<Integer, Integer> test = new HashMap<>();

		for (int i = 0; i < testArray2.length; i++) {
			int similar = 0;
			for (int j = 0; j < testArray1.length; j++) {
				if (Arrays.equals(testArray1[j], testArray2[i])) {
					similar++;
				}
			}
			test.put(i, similar);
		}

		Integer[] sortedIndices = new Integer[test.size()];
		for (int index = 0; index < test.size(); index++) {
			sortedIndices[index] = index;
		}

		Arrays.sort(sortedIndices, (Integer o1, Integer o) -> test.get(o)
				.compareTo(test.get(o1)));

		int[][] array = new int[test.size()][];
		for (int index = 0; index < sortedIndices.length; index++) {
			array[index] = testArray2[sortedIndices[index]];
		}

		return array;
	}
}
