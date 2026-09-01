
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

		int i = 0;
		while (i < myArray.length) {
			countArray[myArray[i] - min]++;
			i++;
		}

		int index = 0;
		int[] result = new int[myArray.length];

		int i = 0;
		while (i < countArray.length) {
			for (int j = 0; j < countArray[i]; j++) {
				result[index++] = i + min;
			}
			i++;
		}
		return result;
	}
}
