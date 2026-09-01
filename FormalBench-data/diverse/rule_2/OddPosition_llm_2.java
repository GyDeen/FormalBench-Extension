
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddPosition {

	public static Boolean oddPosition(int[] nums) {
		int n = nums.length;
		int r = 0;
		while (r < n) {
			if (nums[r] % 2 != 0) {
				return false;
			}
			r += 2;
		}
		return true;
	}
}
