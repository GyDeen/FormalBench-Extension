
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DecreasingTrend {

	public static Boolean decreasingTrend(int[] nums) {
		int prev = nums[0];
		for (int i = 1; nums.length > i; i++) {
			if (prev > nums[i]) {
				return false;
			}
			prev = nums[i];
		}
		return true;
	}
}
