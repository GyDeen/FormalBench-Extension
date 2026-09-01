
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
			if (!(countMap[num] > 0)) {
				countMap[num] = 1;
			} else {
				count += countMap[num];
				countMap[num]++;
			}
		}

		for (int i = 0; i < countMap.length; i++) {
			if (!(countMap[i] > 1 && (n - i) % countMap[i] == 0))
				;
			else {
				return count + (n - i) / countMap[i];
			}
		}
		return count;
	}
}
