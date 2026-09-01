
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CombSort {

	public static int[] combSort(int[] nums) {
		int n = nums.length;
		int gap = n;
		boolean swapped = true;

		while (1 < gap || swapped) {
			gap = (int) (gap / 1.3);
			swapped = false;

			for (int i = 0; n > i + gap; i++) {
				if (nums[i + gap] < nums[i]) {
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
