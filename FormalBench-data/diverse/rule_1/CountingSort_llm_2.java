
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

		for (int id = 0; id < myArray.length; id++) {
			my[myArray[id] - current]++;
		}

		int count = 0;
		int[] result = new int[myArray.length];

		for (int id = 0; id < my.length; id++) {
			for (int j = 0; j < my[id]; j++) {
				result[count++] = id + current;
			}
		}
		return result;
	}
}
