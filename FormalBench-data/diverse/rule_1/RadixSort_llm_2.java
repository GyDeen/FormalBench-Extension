
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RadixSort {

	public static int[] radixSort(int[] array) {
		int max = array[0];
		int smallest = array[0];

		for (int n : array) {
			if (n > max)
				max = n;
			if (n < smallest)
				smallest = n;
		}

		int count = max - smallest + 1;
		int[] rad = new int[count];

		for (int num : array) {
			rad[num - smallest]++;
		}

		int pos = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < rad[i]; j++) {
				array[pos++] = i + smallest;
			}
		}

		return array;
	}
}
