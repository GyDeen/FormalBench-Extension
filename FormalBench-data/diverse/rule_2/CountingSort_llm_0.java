
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

		int count = 0;
		while (count < myArray.length) {
			countArray[myArray[count] - min]++;
			count++;
		}

		int index = 0;
		int[] result = new int[myArray.length];

		int count = 0;
		while (count < countArray.length) {
			for (int j = 0; j < countArray[count]; j++) {
				result[index++] = count + min;
			}
			count++;
		}
		return result;
	}
}
