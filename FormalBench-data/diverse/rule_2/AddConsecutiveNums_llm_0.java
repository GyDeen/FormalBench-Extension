
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddConsecutiveNums {

	public static int[] addConsecutiveNums(int[] nums) {
		if (nums.length < 2) {
			return new int[0];
		}
		int[] result = new int[nums.length - 1];
		int num = 0;
		while (num < nums.length - 1) {
			result[num] = nums[num] + nums[num + 1];
			num++;
		}
		return result;
	}
}
