
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecreasingTrend {

	public static Boolean decreasingTrend(int[] nums) {
		int prev = nums[0];
		int last = 1;
		while (last < nums.length) {
			if (nums[last] < prev) {
				return false;
			}
			prev = nums[last];
			last++;
		}
		return true;
	}
}
