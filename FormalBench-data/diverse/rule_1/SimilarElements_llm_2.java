
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
		for (int id : array2) {
			if (elements.contains(id)) {
				set.add(id);
			}
		}

		int[] resultArray = new int[set.size()];
		int index = 0;
		for (int id : set) {
			resultArray[index++] = id;
		}
		Arrays.sort(resultArray);

		return resultArray;
	}
}
