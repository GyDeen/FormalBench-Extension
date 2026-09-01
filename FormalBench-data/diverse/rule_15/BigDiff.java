
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigDiff {

	public static int bigDiff(int[] nums) {
		int max = nums[0];
		int min = nums[0];
		for (int i = 1; i < nums.length; i++) {
			max = (nums[i] > max) ? nums[i] : max;
			min = (nums[i] < min) ? nums[i] : min;
		}
		return max - min;
	}
}
