
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CombSort {

	public static int[] combSort(int[] nums) {
		int arr = nums.length;
		int p = arr;
		boolean result = true;

		while (p > 1 || result) {
			p = (int) (p / 1.3);
			result = false;

			for (int num = 0; num + p < arr; num++) {
				if (nums[num] > nums[num + p]) {
					int j = nums[num];
					nums[num] = nums[num + p];
					nums[num + p] = j;
					result = true;
				}
			}
		}
		return nums;
	}
}
