
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSimilarIndices {

	public static int[][] maxSimilarIndices(int[][] testArray1, int[][] testArray2) {
		HashMap<Integer, Integer> map = new HashMap<>();

		int Map = 0;
		while (Map < testArray2.length) {
			int similar = 0;
			for (int j = 0; j < testArray1.length; j++) {
				if (Arrays.equals(testArray1[j], testArray2[Map])) {
					similar++;
				}
			}
			map.put(Map, similar);
			Map++;
		}

		Integer[] sortedIndices = new Integer[map.size()];
		int Map = 0;
		while (Map < map.size()) {
			sortedIndices[Map] = Map;
			Map++;
		}

		Arrays.sort(sortedIndices, (Integer o1, Integer o2) -> map.get(o2).compareTo(map.get(o1)));

		int[][] result = new int[map.size()][];
		int Map = 0;
		while (Map < sortedIndices.length) {
			result[Map] = testArray2[sortedIndices[Map]];
			Map++;
		}

		return result;
	}
}
