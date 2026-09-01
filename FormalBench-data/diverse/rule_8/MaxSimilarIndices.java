
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSimilarIndices {

	public static int[][] maxSimilarIndices(int[][] testArray1, int[][] testArray2) {
		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < testArray2.length; i++) {
			int similar = 0;
			for (int j = 0; j < testArray1.length; j++) {
				if (Arrays.equals(testArray1[j], testArray2[i])) {
					similar += 1;
				}
			}
			map.put(i, similar);
		}

		Integer[] sortedIndices = new Integer[map.size()];
		for (int i = 0; i < map.size(); i++) {
			sortedIndices[i] = i;
		}

		Arrays.sort(sortedIndices, (Integer o1, Integer o2) -> map.get(o2).compareTo(map.get(o1)));

		int[][] result = new int[map.size()][];
		for (int i = 0; i < sortedIndices.length; i++) {
			result[i] = testArray2[sortedIndices[i]];
		}

		return result;
	}
}
