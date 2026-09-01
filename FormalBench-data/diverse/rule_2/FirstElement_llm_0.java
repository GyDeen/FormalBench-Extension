
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

		int count = 0;
		while (count < n) {
			countMap.put(arr[count], countMap.getOrDefault(arr[count], 0) + 1);
			count++;
		}

		int count = 0;
		while (count < n) {
			if (countMap.get(arr[count]) != null && countMap.get(arr[count]) == k) {
				return arr[count];
			}
			count++;
		}

		return -1;
	}
}
