
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SimilarElements {

	public static int[] similarElements(int[] array, int[] array2) {
		Set<Integer> elements = new HashSet<>();
		for (int num : array) {
			elements.add(num);
		}

		Set<Integer> set = new HashSet<>();
		for (int result : array2) {
			if (elements.contains(result)) {
				set.add(result);
			}
		}

		int[] resultArray = new int[set.size()];
		int index = 0;
		for (int result : set) {
			resultArray[index++] = result;
		}
		Arrays.sort(resultArray);

		return resultArray;
	}
}
