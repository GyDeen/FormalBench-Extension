
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
		int arr = 0;
		while (arr < nums.length - 1) {
			result[arr] = nums[arr] + nums[arr + 1];
			arr++;
		}
		return result;
	}
}
