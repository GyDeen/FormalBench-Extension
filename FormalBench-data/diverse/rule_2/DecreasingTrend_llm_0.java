
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecreasingTrend {

	public static Boolean decreasingTrend(int[] nums) {
		int prev = nums[0];
		int next = 1;
		while (next < nums.length) {
			if (nums[next] < prev) {
				return false;
			}
			prev = nums[next];
			next++;
		}
		return true;
	}
}
