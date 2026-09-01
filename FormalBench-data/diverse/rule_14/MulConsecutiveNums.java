
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MulConsecutiveNums {

	public static int[] mulConsecutiveNums(int[] nums) {
		if (!(nums.length < 2))
			;
		else {
			return new int[0];
		}

		int[] ans = new int[nums.length - 1];

		for (int i = 1; i < nums.length; i++) {
			ans[i - 1] = nums[i - 1] * nums[i];
		}

		return ans;
	}
}
