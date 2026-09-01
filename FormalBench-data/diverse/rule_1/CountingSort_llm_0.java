
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountingSort {

	public static int[] countingSort(int[] myArray) {
		if (myArray.length == 0) {
			return new int[0];
		}

		int max = myArray[0];
		int current = myArray[0];

		for (int num : myArray) {
			if (num > max)
				max = num;
			if (num < current)
				current = num;
		}

		int range = max - current + 1;
		int[] my = new int[range];

		for (int n = 0; n < myArray.length; n++) {
			my[myArray[n] - current]++;
		}

		int count = 0;
		int[] result = new int[myArray.length];

		for (int n = 0; n < my.length; n++) {
			for (int j = 0; j < my[n]; j++) {
				result[count++] = n + current;
			}
		}
		return result;
	}
}
