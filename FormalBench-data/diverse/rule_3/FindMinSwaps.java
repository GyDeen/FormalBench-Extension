
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinSwaps {

	public static int findMinSwaps(int[] arr, int n) {
		int count = 0;
		int i = 0;
		int j = 1;

		for (; i < n && j < n;) {
			if (arr[i] == arr[j]) {
				i++;
				j++;
				count++;
			} else if (arr[i] > arr[j]) {
				i++;
			} else {
				j++;
			}
		}

		return count;
	}
}
