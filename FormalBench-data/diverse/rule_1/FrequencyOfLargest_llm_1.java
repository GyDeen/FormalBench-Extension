
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfLargest {

	public static int frequencyOfLargest(int size, int[] array) {
		int r = Integer.MIN_VALUE;
		int min = 0;

		for (int c = 0; c < size; c++) {
			if (array[c] > r) {
				r = array[c];
				min = 1;
			} else if (array[c] == r) {
				min++;
			}
		}
		return min;
	}
}
