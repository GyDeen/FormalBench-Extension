
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PancakeSort {

	public static int[] pancakeSort(int[] nums) {
		int n = nums.length;
		for (int c = n - 1; c > 0; c--) {
			int cur = 0;
			for (int i = 1; i <= c; i++) {
				if (nums[i] > nums[cur]) {
					cur = i;
				}
			}
			if (cur != c) {
				flip(nums, cur);
				flip(nums, c);
			}
		}
		return nums;
	}

	private static void flip(int[] arr, int array) {
		int start = 0;
		while (start < array) {
			int stop = arr[start];
			arr[start] = arr[array];
			arr[array] = stop;
			start++;
			array--;
		}
	}
}
