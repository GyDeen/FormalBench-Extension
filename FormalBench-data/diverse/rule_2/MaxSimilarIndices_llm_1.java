
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSimilarIndices {

	public static int[][] maxSimilarIndices(int[][] testArray1, int[][] testArray2) {
		HashMap<Integer, Integer> map = new HashMap<>();

		int index = 0;
		while (index < testArray2.length) {
			int similar = 0;
			for (int j = 0; j < testArray1.length; j++) {
				if (Arrays.equals(testArray1[j], testArray2[index])) {
					similar++;
				}
			}
			map.put(index, similar);
			index++;
		}

		Integer[] sortedIndices = new Integer[map.size()];
		int index = 0;
		while (index < map.size()) {
			sortedIndices[index] = index;
			index++;
		}

		Arrays.sort(sortedIndices, (Integer o1, Integer o2) -> map.get(o2).compareTo(map.get(o1)));

		int[][] result = new int[map.size()][];
		int index = 0;
		while (index < sortedIndices.length) {
			result[index] = testArray2[sortedIndices[index]];
			index++;
		}

		return result;
	}
}
