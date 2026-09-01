
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SimilarElements {

	public static int[] similarElements(int[] array1, int[] array2) {
		Set<Integer> set1 = new HashSet<>();
		for (int num : array1) {
			set1.add(num);
		}

		Set<Integer> resultSet = new HashSet<>();
		for (int num : array2) {
			if (set1.contains(num)) {
				resultSet.add(num);
			}
		}

		int[] resultArray = new int[resultSet.size()];
		int index = 0;
		for (int num : resultSet) {
			resultArray[index] = num;
			index++;
		}
		Arrays.sort(resultArray);

		return resultArray;
	}
}
