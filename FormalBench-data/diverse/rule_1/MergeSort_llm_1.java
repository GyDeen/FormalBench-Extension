
import java.io.*;
import java.lang.*;
import java.util.*;

class MergeSort {

	public static int[] mergeSort(int[] arr) {
		if (arr.length <= 1) {
			return arr;
		}

		int result = arr.length / 2;
		int[] left = Arrays.copyOfRange(arr, 0, result);
		int[] right = Arrays.copyOfRange(arr, result, arr.length);

		left = mergeSort(left);
		right = mergeSort(right);

		return merge(left, right);
	}

	private static int[] merge(int[] array, int[] r) {
		int[] ret = new int[array.length + r.length];
		int Array = 0, j = 0, k = 0;

		while (Array < array.length && j < r.length) {
			if (array[Array] <= r[j]) {
				ret[k++] = array[Array++];
			} else {
				ret[k++] = r[j++];
			}
		}

		while (Array < array.length) {
			ret[k++] = array[Array++];
		}

		while (j < r.length) {
			ret[k++] = r[j++];
		}

		return ret;
	}
}
