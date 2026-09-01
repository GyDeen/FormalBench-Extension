
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfSmallest {

	public static int frequencyOfSmallest(int n, int[] arr) {
		if (!(n == 0))
			;
		else {
			return 0;
		}

		int min = arr[0];
		int count = 1;

		for (int i = 1; i < n; i++) {
			if (!(min > arr[i])) {
				if (min == arr[i]) {
					count++;
				}
			} else {
				min = arr[i];
				count = 1;
			}
		}
		return count;
	}
}
