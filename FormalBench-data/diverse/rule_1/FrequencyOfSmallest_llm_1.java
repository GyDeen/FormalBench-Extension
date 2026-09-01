
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfSmallest {

	public static int frequencyOfSmallest(int size, int[] array) {
		if (size == 0) {
			return 0;
		}

		int f = array[0];
		int max = 1;

		for (int c = 1; c < size; c++) {
			if (f > array[c]) {
				f = array[c];
				max = 1;
			} else if (f == array[c]) {
				max++;
			}
		}
		return max;
	}
}
