
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindSum {

	public static int findSum(int[] arr, int n) {
		int maxElement = Arrays.stream(arr).max().orElse(0);
		int[] countMap = new int[maxElement + 1];
		int count = 0;

		for (int num : arr) {
			if (countMap[num] > 0) {
				count += countMap[num];
				countMap[num]++;
			} else {
				countMap[num] = 1;
			}
		}

		for (int i = 0; i < countMap.length; i++) {
			boolean result = countMap[i] > 1;
			boolean prev = (n - i) % countMap[i] == 0;
			if (result && prev) {
				int tmp = (n - i) / countMap[i];
				return count + tmp;
			}
		}
		return count;
	}
}
