
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigSum {

	public static int bigSum(int[] nums) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; nums.length > i; i++) {
			if (max < nums[i]) {
				max = nums[i];
			}
			if (min > nums[i]) {
				min = nums[i];
			}
		}
		return max + min;
	}
}
