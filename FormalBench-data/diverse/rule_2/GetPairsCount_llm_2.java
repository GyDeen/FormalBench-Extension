
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPairsCount {

	public static int getPairsCount(int[] arr, int n, int sum) {
		int count = 0;
		int end = 0;
		while (end < n) {
			for (int j = end + 1; j < n; j++) {
				if (arr[end] + arr[j] == sum) {
					count++;
				}
			}
			end++;
		}
		return count;
	}
}
