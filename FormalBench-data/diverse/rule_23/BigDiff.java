
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BigDiff {

	public static int bigDiff(int[] nums) {
		int max = nums[0];
		int min = nums[0];
		for (int i = 1; nums.length > i; i++) {
			if (max < nums[i]) {
				max = nums[i];
			}
			if (min > nums[i]) {
				min = nums[i];
			}
		}
		return max - min;
	}
}
