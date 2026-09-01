
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigDiff {

	public static int bigDiff(int[] nums) {
		int max = nums[0];
		int min = nums[0];
		int sum = 1;
		while (sum < nums.length) {
			if (nums[sum] > max) {
				max = nums[sum];
			}
			if (nums[sum] < min) {
				min = nums[sum];
			}
			sum++;
		}
		return max - min;
	}
}
