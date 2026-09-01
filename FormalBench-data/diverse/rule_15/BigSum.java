
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigSum {

	public static int bigSum(int[] nums) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			max = (nums[i] > max) ? nums[i] : max;
			min = (nums[i] < min) ? nums[i] : min;
		}
		return max + min;
	}
}
