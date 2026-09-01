
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SimilarElements {

	public static int[] similarElements(int[] array1, int[] array2) {
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> resultSet = new HashSet<>();

		for (int num : array1) {
			set1.add(num);
		}
		for (int num : array2) {
			if (set1.contains(num)) {
				resultSet.add(num);
			}
		}

		int index = 0;
		int[] resultArray = new int[resultSet.size()];
		for (int num : resultSet) {
			resultArray[index++] = num;
		}
		Arrays.sort(resultArray);

		return resultArray;
	}
}
