
import java.io.*;
import java.lang.*;
import java.util.*;

class MergeSort {

	public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid;
		mid = arr.length / 2
		int[] left;
		left = Arrays.copyOfRange(arr, 0, mid)
		int[] right;
		right = Arrays.copyOfRange(arr, mid, arr.length)
		left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

	private static int[] merge(int[] left, int[] right) {
        int[] merged;
		merged = new int[left.length + right.length]
		int k = 0;
		int j = 0;
		int i = 0;
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
