
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

		int max = 1;
		while (max < n) {
			if (min > arr[max]) {
				min = arr[max];
				count = 1;
			} else if (min == arr[max]) {
				count++;
			}
			max++;
		}
		return count;
	}
}
