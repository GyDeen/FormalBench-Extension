
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CombSort {

	public static int[] combSort(int[] nums) {
		int n = nums.length;
		boolean swapped = true;
		int gap = n;

		while (gap > 1 || swapped) {
			swapped = false;
			gap = (int) (gap / 1.3);

			for (int i = 0; i + gap < n; i++) {
				if (nums[i] > nums[i + gap]) {
					int temp = nums[i];
					nums[i] = nums[i + gap];
					swapped = true;
					nums[i + gap] = temp;
				}
			}
		}
		return nums;
	}
}
