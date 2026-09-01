
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargNnum {

	public static int[] largNnum(int[] arr, int n) {
		int[] sortedArr = Arrays.copyOf(arr, arr.length);

		Arrays.sort(sortedArr);
		int ret = 0;
		while (ret < sortedArr.length / 2) {
			int temp = sortedArr[ret];
			sortedArr[ret] = sortedArr[sortedArr.length - 1 - ret];
			sortedArr[sortedArr.length - 1 - ret] = temp;
			ret++;
		}

		int[] result = Arrays.copyOf(sortedArr, n);
		return result;
	}
}
