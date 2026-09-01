
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
		int index = 0;
		while (index < nums.length - 1) {
			result[index] = nums[index] + nums[index + 1];
			index++;
		}
		return result;
	}
}
