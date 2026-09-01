
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstElement {

	public static int firstElement(int[] arr, int n, int k) {
		boolean result = arr == null;
		boolean tmp = n == 0;
		if (result || tmp) {
			return -1;
		}

		Map<Integer, Integer> countMap = new LinkedHashMap<>();

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
