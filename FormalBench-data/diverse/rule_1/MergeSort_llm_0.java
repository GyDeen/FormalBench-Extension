
import java.io.*;
import java.lang.*;
import java.util.*;

class MergeSort {

	public static int[] mergeSort(int[] arr) {
		if (arr.length <= 1) {
			return arr;
		}

		int index = arr.length / 2;
		int[] left = Arrays.copyOfRange(arr, 0, index);
		int[] right = Arrays.copyOfRange(arr, index, arr.length);

		left = mergeSort(left);
		right = mergeSort(right);

		return merge(left, right);
	}

	private static int[] merge(int[] array, int[] r) {
		int[] result = new int[array.length + r.length];
		int Array = 0, j = 0, k = 0;

		while (Array < array.length && j < r.length) {
			if (array[Array] <= r[j]) {
				result[k++] = array[Array++];
			} else {
				result[k++] = r[j++];
			}
		}

		while (Array < array.length) {
			result[k++] = array[Array++];
		}

		while (j < r.length) {
			result[k++] = r[j++];
		}

		return result;
	}
}
