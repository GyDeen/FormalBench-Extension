
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
		int[] right = Arrays.copyOfRange(arr, mid, arr.length);

		left = mergeSort(left);
		right = mergeSort(right);

		return merge(left, right);
	}

	private static int[] merge(int[] left, int[] right) {
		int[] merged = new int[left.length + right.length];
		int i = 0, j = 0, k = 0;

		boolean result = i < left.length;
		boolean tmp = j < right.length;
		while (result && tmp) {
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
