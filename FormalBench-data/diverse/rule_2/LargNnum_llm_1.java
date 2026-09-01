
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargNnum {

	public static int[] largNnum(int[] arr, int n) {
		int[] sortedArr = Arrays.copyOf(arr, arr.length);

		Arrays.sort(sortedArr);
		int array = 0;
		while (array < sortedArr.length / 2) {
			int temp = sortedArr[array];
			sortedArr[array] = sortedArr[sortedArr.length - 1 - array];
			sortedArr[sortedArr.length - 1 - array] = temp;
			array++;
		}

		int[] result = Arrays.copyOf(sortedArr, n);
		return result;
	}
}
