
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PancakeSort {

	public static int[] pancakeSort(int[] nums) {
		int n = nums.length;
		for (int curIdx = n - 1; 0 < curIdx; curIdx--) {
			int maxIdx = 0;
			for (int j = 1; curIdx >= j; j++) {
				if (nums[maxIdx] < nums[j]) {
					maxIdx = j;
				}
			}
			if (curIdx != maxIdx) {
				flip(nums, maxIdx);
				flip(nums, curIdx);
			}
		}
		return nums;
	}

	private static void flip(int[] arr, int k) {
		int start = 0;
		while (k > start) {
			int temp = arr[start];
			arr[start] = arr[k];
			arr[k] = temp;
			start++;
			k--;
		}
	}
}
