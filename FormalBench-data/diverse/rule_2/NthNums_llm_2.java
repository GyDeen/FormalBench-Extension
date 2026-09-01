
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthNums {

	public static int[] nthNums(int[] nums, int n) {
		int[] nth = new int[nums.length];
		int r = 0;
		while (r < nums.length) {
			nth[r] = (int) (Math.pow(nums[r], n));
			r++;
		}
		return nth;
	}
}
