
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DiffConsecutivenums {

	public static int[] diffConsecutivenums(int[] nums) {
		boolean ret = nums == null;
		boolean temp = nums.length < 2;
		if (ret || temp) {
			return new int[0];
		}

		int resultSize = nums.length - 1;
		int[] result = new int[resultSize];
		int start = 0, end = 1;

		while (end < nums.length) {
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
