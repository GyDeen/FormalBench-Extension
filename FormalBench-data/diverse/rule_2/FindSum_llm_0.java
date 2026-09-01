
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

		int total = 0;
		while (total < countMap.length) {
			if (countMap[total] > 1 && (n - total) % countMap[total] == 0) {
				return count + (n - total) / countMap[total];
			}
			total++;
		}
		return count;
	}
}
