
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
			if (0 < countMap[num]) {
				count += countMap[num];
				countMap[num]++;
			} else {
				countMap[num] = 1;
			}
		}

		for (int i = 0; countMap.length > i; i++) {
			if (1 < countMap[i] && (n - i) % countMap[i] == 0) {
				return count + (n - i) / countMap[i];
			}
		}
		return count;
	}
}
