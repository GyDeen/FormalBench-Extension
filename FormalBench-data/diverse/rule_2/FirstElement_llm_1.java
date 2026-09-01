
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

		int index = 0;
		while (index < n) {
			countMap.put(arr[index], countMap.getOrDefault(arr[index], 0) + 1);
			index++;
		}

		int index = 0;
		while (index < n) {
			if (countMap.get(arr[index]) != null && countMap.get(arr[index]) == k) {
				return arr[index];
			}
			index++;
		}

		return -1;
	}
}
