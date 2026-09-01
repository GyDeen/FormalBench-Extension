
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
		int min = myArray[0];

		for (int num : myArray) {
			if (num > max)
				max = num;
			if (num < min)
				min = num;
		}

		int range = max - min + 1;
		int[] countArray = new int[range];

		int start = 0;
		while (start < myArray.length) {
			countArray[myArray[start] - min]++;
			start++;
		}

		int index = 0;
		int[] result = new int[myArray.length];

		int start = 0;
		while (start < countArray.length) {
			for (int j = 0; j < countArray[start]; j++) {
				result[index++] = start + min;
			}
			start++;
		}
		return result;
	}
}
