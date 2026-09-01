
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecreasingTrend {

	public static Boolean decreasingTrend(int[] nums) {
		int prev = nums[0];
		int cur = 1;
		while (cur < nums.length) {
			if (nums[cur] < prev) {
				return false;
			}
			prev = nums[cur];
			cur++;
		}
		return true;
	}
}
