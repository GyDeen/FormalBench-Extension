
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

			int next = 0;
			while (next + gap < n) {
				if (nums[next] > nums[next + gap]) {
					int temp = nums[next];
					nums[next] = nums[next + gap];
					nums[next + gap] = temp;
					swapped = true;
				}
				next++;
			}
		}
		return nums;
	}
}
