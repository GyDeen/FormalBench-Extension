
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigDiff {

	public static int bigDiff(int[] nums) {
		int max = nums[0];
		int min = nums[0];
		int diff = 1;
		while (diff < nums.length) {
			if (nums[diff] > max) {
				max = nums[diff];
			}
			if (nums[diff] < min) {
				min = nums[diff];
			}
			diff++;
		}
		return max - min;
	}
}
