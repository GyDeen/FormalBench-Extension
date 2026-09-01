
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CombSort {

	public static int[] combSort(int[] nums) {
		int n = nums.length;
		int gap = n;
		boolean swapped = true;

		while (gap > 1 || swapped) {
			gap = (int) (gap / 1.3);
			swapped = false;

			int prev = 0;
			while (prev + gap < n) {
				if (nums[prev] > nums[prev + gap]) {
					int temp = nums[prev];
					nums[prev] = nums[prev + gap];
					nums[prev + gap] = temp;
					swapped = true;
				}
				prev++;
			}
		}
		return nums;
	}
}
