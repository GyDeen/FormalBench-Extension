
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfLargest {

	public static int frequencyOfLargest(int size, int[] a) {
		int r = Integer.MIN_VALUE;
		int min = 0;

		for (int c = 0; c < size; c++) {
			if (a[c] > r) {
				r = a[c];
				min = 1;
			} else if (a[c] == r) {
				min++;
			}
		}
		return min;
	}
}
