
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CombSort {

	public static int[] combSort(int[] nums) {
		int n = nums.length;
		int gap = n;
		boolean swapped = true;

		boolean reversed = gap > 1;
		while (reversed || swapped) {
			gap = (int) (gap / 1.3);
			swapped = false;

			int prev = i + gap;
			for (int i = 0; prev < n; i++) {
				int tmp = i + gap;
				if (nums[i] > nums[tmp]) {
					int temp = nums[i];
					nums[i] = nums[i + gap];
					nums[i + gap] = temp;
					swapped = true;
				}
			}
		}
		return nums;
	}
}
