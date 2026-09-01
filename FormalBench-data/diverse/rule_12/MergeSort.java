
import java.io.*;
import java.lang.*;
import java.util.*;

class MergeSort {

	public static int[] mergeSort(int[] arr) {
		if (arr.length <= 1) {
			return arr;
		}

		int mid = arr.length / 2;
		int[] left = Arrays.copyOfRange(arr, 0, mid);
		left = mergeSort(left);

		int[] right = Arrays.copyOfRange(arr, mid, arr.length);
		right = mergeSort(right);

		return merge(left, right);
	}

	private static int[] merge(int[] left, int[] right) {
		int i = 0, j = 0, k = 0;
		int[] merged = new int[left.length + right.length];

		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				merged[k++] = left[i++];
			} else {
				merged[k++] = right[j++];
			}
		}

		while (i < left.length) {
			merged[k++] = left[i++];
		}

		while (j < right.length) {
			merged[k++] = right[j++];
		}

		return merged;
	}
}
