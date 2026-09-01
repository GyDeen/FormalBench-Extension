
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MulConsecutiveNums {

	public static int[] mulConsecutiveNums(int[] nums) {
		if (nums.length < 2) {
			return new int[0];
		}

		int[] ans = new int[nums.length - 1];

		int result = 1;
		while (result < nums.length) {
			ans[result - 1] = nums[result - 1] * nums[result];
			result++;
		}

		return ans;
	}
}
