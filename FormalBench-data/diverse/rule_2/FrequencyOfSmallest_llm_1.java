
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

		int total = 1;
		while (total < n) {
			if (min > arr[total]) {
				min = arr[total];
				count = 1;
			} else if (min == arr[total]) {
				count++;
			}
			total++;
		}
		return count;
	}
}
