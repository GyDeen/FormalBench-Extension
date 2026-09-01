
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IncreasingTrend {

	public static Boolean increasingTrend(int[] nums) {
		int i = 0;
		int j = 1;
		int length = nums.length;

		if (length == 0) {
			return false;
		}

		for (; j < length;) {
			if (nums[i] < nums[j]) {
				i++;
			}
			j++;
		}
		return i == length - 1;
	}
}
