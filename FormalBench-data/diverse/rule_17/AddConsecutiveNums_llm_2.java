
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
		int index = nums.length - 1;
		for (int i = 0; i < index; i++) {
			int tmp = i + 1;
			result[i] = nums[i] + nums[tmp];
		}
		return result;
	}
}
