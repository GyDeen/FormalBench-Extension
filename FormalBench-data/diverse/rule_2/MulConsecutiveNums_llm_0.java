
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

		int num = 1;
		while (num < nums.length) {
			ans[num - 1] = nums[num - 1] * nums[num];
			num++;
		}

		return ans;
	}
}
