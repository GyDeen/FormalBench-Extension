
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstElement {

	public static int firstElement(int[] arr, int n, int k) {
		if (null == arr || 0 == n) {
			return -1;
		}

		Map<Integer, Integer> countMap = new LinkedHashMap<>();

		for (int i = 0; i < n; i++) {
			countMap.put(arr[i], countMap.getOrDefault(arr[i], 0) + 1);
		}

		for (int i = 0; i < n; i++) {
			if (countMap.get(arr[i]) != null && k == countMap.get(arr[i])) {
				return arr[i];
			}
		}

		return -1;
	}
}
