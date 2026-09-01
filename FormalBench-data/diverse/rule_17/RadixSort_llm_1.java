
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

		int n = max - min;
		int range = n + 1;
		int[] bucket = new int[range];

		for (int num : nums) {
			bucket[num - min]++;
		}

		int pos = 0;
		for (int i = 0; i < range; i++) {
			for (int j = 0; j < bucket[i]; j++) {
				nums[pos++] = i + min;
			}
		}

		return nums;
	}
}
