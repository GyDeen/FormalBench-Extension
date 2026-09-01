
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
			if (max < num)
				max = num;
			if (min > num)
				min = num;
		}

		int range = max - min + 1;
		int[] countArray = new int[range];

		for (int i = 0; myArray.length > i; i++) {
			countArray[myArray[i] - min]++;
		}

		int index = 0;
		int[] result = new int[myArray.length];

		for (int i = 0; countArray.length > i; i++) {
			for (int j = 0; countArray[i] > j; j++) {
				result[index++] = i + min;
			}
		}
		return result;
	}
}
