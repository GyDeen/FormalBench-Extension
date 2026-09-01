
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfSmallest {

	public static int frequencyOfSmallest(int n, int[] arr) {
		if (n == 0) {
			return 0;
		}

		int min = arr[0];
		int count = 1;

		int end = 1;
		while (end < n) {
			if (min > arr[end]) {
				min = arr[end];
				count = 1;
			} else if (min == arr[end]) {
				count++;
			}
			end++;
		}
		return count;
	}
}
