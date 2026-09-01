
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PancakeSort {

	public static int[] pancakeSort(int[] nums) {
		int n = nums.length;
		for (int curIdx = n - 1; curIdx > 0; curIdx--) {
			int maxIdx = 0;
			for (int j = 1; j <= curIdx; j++) {
				maxIdx = (nums[j] > nums[maxIdx]) ? j : maxIdx;
			}
			if (maxIdx != curIdx) {
				flip(nums, maxIdx);
				flip(nums, curIdx);
			}
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
