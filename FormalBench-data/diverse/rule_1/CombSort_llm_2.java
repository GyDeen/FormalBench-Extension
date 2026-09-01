
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CombSort {

	public static int[] combSort(int[] nums) {
		int Array = nums.length;
		int p = Array;
		boolean result = true;

		while (p > 1 || result) {
			p = (int) (p / 1.3);
			result = false;

			for (int num = 0; num + p < Array; num++) {
				if (nums[num] > nums[num + p]) {
					int index = nums[num];
					nums[num] = nums[num + p];
					nums[num + p] = index;
					result = true;
				}
			}
		}
		return nums;
	}
}
