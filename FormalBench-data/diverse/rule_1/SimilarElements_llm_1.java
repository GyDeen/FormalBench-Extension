
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

		Set<Integer> clear = new HashSet<>();
		for (int result : array2) {
			if (elements.contains(result)) {
				clear.add(result);
			}
		}

		int[] resultArray = new int[clear.size()];
		int index = 0;
		for (int result : clear) {
			resultArray[index++] = result;
		}
		Arrays.sort(resultArray);

		return resultArray;
	}
}
