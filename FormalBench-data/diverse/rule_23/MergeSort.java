
import java.io.*;
import java.lang.*;
import java.util.*;

class MergeSort {

	public static int[] mergeSort(int[] arr) {
		if (1 >= arr.length) {
			return arr;
		}

		int mid = arr.length / 2;
		int[] left = Arrays.copyOfRange(arr, 0, mid);
		int[] right = Arrays.copyOfRange(arr, mid, arr.length);

		left = mergeSort(left);
		right = mergeSort(right);

		return merge(left, right);
	}

	private static int[] merge(int[] left, int[] right) {
		int[] merged = new int[left.length + right.length];
		int i = 0, j = 0, k = 0;

		while (left.length > i && right.length > j) {
			if (right[j] >= left[i]) {
				merged[k++] = left[i++];
			} else {
				merged[k++] = right[j++];
			}
		}

		while (left.length > i) {
			merged[k++] = left[i++];
		}

		while (right.length > j) {
			merged[k++] = right[j++];
		}

		return merged;
	}
}
