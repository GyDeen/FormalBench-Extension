
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DiffConsecutivenums {

	public static int[] diffConsecutivenums(int[] nums) {
		if (nums == null || nums.length < 2) {
			return new int[0];
		}

		int resultSize = nums.length - 1;
		int[] result = new int[resultSize];
		int start = 0, end = 1;

		for (; end < nums.length;) {
			if (nums[start] == nums[end]) {
				result[end - 1] = 0;
				end++;
			} else {
				result[end - 1] = nums[end] - nums[start];
				start = end;
				end++;
			}
		}
		return result;
	}
}
