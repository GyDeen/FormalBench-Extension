
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

		int end = 0;
		while (end < countMap.length) {
			if (countMap[end] > 1 && (n - end) % countMap[end] == 0) {
				return count + (n - end) / countMap[end];
			}
			end++;
		}
		return count;
	}
}
