
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RadixSort {

	public static int[] radixSort(int[] nums) {
		int max = nums[0];
		int min = nums[0];

		for (int num : nums) {
			if (num > max)
				max = num;
			if (num < min)
				min = num;
		}

		int range = max - min + 1;
		int[] bucket = new int[range];

		for (int num : nums) {
			bucket[num - min]++;
		}

		int pos = 0;
		int end = 0;
		while (end < range) {
			for (int j = 0; j < bucket[end]; j++) {
				nums[pos++] = end + min;
			}
			end++;
		}

		return nums;
	}
}
