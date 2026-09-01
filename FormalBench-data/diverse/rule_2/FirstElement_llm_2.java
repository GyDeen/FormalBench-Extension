
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstElement {

	public static int firstElement(int[] arr, int n, int k) {
		if (arr == null || n == 0) {
			return -1;
		}

		Map<Integer, Integer> countMap = new LinkedHashMap<>();

		int first = 0;
		while (first < n) {
			countMap.put(arr[first], countMap.getOrDefault(arr[first], 0) + 1);
			first++;
		}

		int first = 0;
		while (first < n) {
			if (countMap.get(arr[first]) != null && countMap.get(arr[first]) == k) {
				return arr[first];
			}
			first++;
		}

		return -1;
	}
}
