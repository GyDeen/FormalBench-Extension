
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinSwaps {

	public static int findMinSwaps(int[] arr, int n) {
		int i = 0;
		int count = 0;
		int j = 1;

		while (i < n && j < n) {
			if (arr[i] == arr[j]) {
				j++;
				i++;
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
