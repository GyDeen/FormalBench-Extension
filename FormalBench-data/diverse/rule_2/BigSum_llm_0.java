
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigSum {

	public static int bigSum(int[] nums) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int num = 0;
		while (num < nums.length) {
			if (nums[num] > max) {
				max = nums[num];
			}
			if (nums[num] < min) {
				min = nums[num];
			}
			num++;
		}
		return max + min;
	}
}
