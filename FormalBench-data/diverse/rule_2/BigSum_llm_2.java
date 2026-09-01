
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigSum {

	public static int bigSum(int[] nums) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int floor = 0;
		while (floor < nums.length) {
			if (nums[floor] > max) {
				max = nums[floor];
			}
			if (nums[floor] < min) {
				min = nums[floor];
			}
			floor++;
		}
		return max + min;
	}
}
