
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargNnum {

	public static int[] largNnum(int[] arr, int n) {
		int[] sortedArr = Arrays.copyOf(arr, arr.length);

		Arrays.sort(sortedArr);
		int Array = 0;
		while (Array < sortedArr.length / 2) {
			int temp = sortedArr[Array];
			sortedArr[Array] = sortedArr[sortedArr.length - 1 - Array];
			sortedArr[sortedArr.length - 1 - Array] = temp;
			Array++;
		}

		int[] result = Arrays.copyOf(sortedArr, n);
		return result;
	}
}
