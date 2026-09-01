
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PancakeSort {

	public static int[] pancakeSort(int[] nums) {
		int n = nums.length;
		for (int i = n - 1; i > 0; i--) {
			int cur = 0;
			for (int max = 1; max <= i; max++) {
				if (nums[max] > nums[cur]) {
					cur = max;
				}
			}
			if (cur != i) {
				flip(nums, cur);
				flip(nums, i);
			}
		}
		return nums;
	}

	private static void flip(int[] arr, int array) {
		int start = 0;
		while (start < array) {
			int end = arr[start];
			arr[start] = arr[array];
			arr[array] = end;
			start++;
			array--;
		}
	}
}
