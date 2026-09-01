
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FrequencyOfSmallest {

	public static int frequencyOfSmallest(int size, int[] a) {
		if (size == 0) {
			return 0;
		}

		int f = a[0];
		int total = 1;

		for (int c = 1; c < size; c++) {
			if (f > a[c]) {
				f = a[c];
				total = 1;
			} else if (f == a[c]) {
				total++;
			}
		}
		return total;
	}
}
