
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

		int arr = 1;
		while (arr < nums.length) {
			ans[arr - 1] = nums[arr - 1] * nums[arr];
			arr++;
		}

		return ans;
	}
}
