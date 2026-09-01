
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSimilarIndices {

	public static int[][] maxSimilarIndices(int[][] testArray1, int[][] testArray2) {
		HashMap<Integer, Integer> map = new HashMap<>();

		int array = 0;
		while (array < testArray2.length) {
			int similar = 0;
			for (int j = 0; j < testArray1.length; j++) {
				if (Arrays.equals(testArray1[j], testArray2[array])) {
					similar++;
				}
			}
			map.put(array, similar);
			array++;
		}

		Integer[] sortedIndices = new Integer[map.size()];
		int array = 0;
		while (array < map.size()) {
			sortedIndices[array] = array;
			array++;
		}

		Arrays.sort(sortedIndices, (Integer o1, Integer o2) -> map.get(o2).compareTo(map.get(o1)));

		int[][] result = new int[map.size()][];
		int array = 0;
		while (array < sortedIndices.length) {
			result[array] = testArray2[sortedIndices[array]];
			array++;
		}

		return result;
	}
}
