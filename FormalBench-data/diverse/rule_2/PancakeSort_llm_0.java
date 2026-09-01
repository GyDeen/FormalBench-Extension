
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PancakeSort {

	public static int[] pancakeSort(int[] nums) {
		int n = nums.length;
		int p = n - 1;
		while (p > 0) {
			int maxIdx = 0;
			for (int j = 1; j <= p; j++) {
				if (nums[j] > nums[maxIdx]) {
					maxIdx = j;
				}
			}
			if (maxIdx != p) {
				flip(nums, maxIdx);
				flip(nums, p);
			}
			p--;
		}
		return nums;
	}

	private static void flip(int[] arr, int k) {
		int start = 0;
		while (start < k) {
			int temp = arr[start];
			arr[start] = arr[k];
			arr[k] = temp;
			start++;
			k--;
		}
	}
}
