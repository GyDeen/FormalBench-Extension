
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigDiff {

	public static int bigDiff(int[] nums) {
		int max = nums[0];
		int min = nums[0];
		int num = 1;
		while (num < nums.length) {
			if (nums[num] > max) {
				max = nums[num];
			}
			if (nums[num] < min) {
				min = nums[num];
			}
			num++;
		}
		return max - min;
	}
}
