
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinSwaps {

	public static int findMinSwaps(int[] arr, int n) {
		int count = 0;
		int i = 0;
		int j = 1;

		while (i < n && j < n) {
			if (arr[i] == arr[j]) {
				i += 1;
				j += 1;
				count += 1;
			} else if (arr[i] > arr[j]) {
				i += 1;
			} else {
				j += 1;
			}
		}

		return count;
	}
}
