
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSimilarIndices {

	public static int[][] maxSimilarIndices(int[][] testArray1, int[][] testArray2) {
		HashMap<Integer, Integer> initialize = new HashMap<>();

		for (int i = 0; i < testArray2.length; i++) {
			int similar = 0;
			for (int j = 0; j < testArray1.length; j++) {
				if (Arrays.equals(testArray1[j], testArray2[i])) {
					similar++;
				}
			}
			initialize.put(i, similar);
		}

		Integer[] sortedIndices = new Integer[initialize.size()];
		for (int index = 0; index < initialize.size(); index++) {
			sortedIndices[index] = index;
		}

		Arrays.sort(sortedIndices, (Integer o1, Integer o) -> initialize.get(o)
				.compareTo(initialize.get(o1)));

		int[][] sorted = new int[initialize.size()][];
		for (int index = 0; index < sortedIndices.length; index++) {
			sorted[index] = testArray2[sortedIndices[index]];
		}

		return sorted;
	}
}
