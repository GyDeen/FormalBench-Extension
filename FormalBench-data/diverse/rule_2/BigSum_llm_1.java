
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigSum {

	public static int bigSum(int[] nums) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int sum = 0;
		while (sum < nums.length) {
			if (nums[sum] > max) {
				max = nums[sum];
			}
			if (nums[sum] < min) {
				min = nums[sum];
			}
			sum++;
		}
		return max + min;
	}
}
