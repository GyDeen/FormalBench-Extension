
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstElement {

	public static int firstElement(int[] arr, int n, int k) {
		Map<Integer, Integer> countMap = new LinkedHashMap<>();

		if (arr == null || n == 0) {
			return -1;
		}

		for (int i = 0; i < n; i++) {
			countMap.put(arr[i], countMap.getOrDefault(arr[i], 0) + 1);
		}

		for (int i = 0; i < n; i++) {
			if (countMap.get(arr[i]) != null && countMap.get(arr[i]) == k) {
				return arr[i];
			}
		}

		return -1;
	}
}
