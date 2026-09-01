
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthNums {

	public static int[] nthNums(int[] nums, int n) {
		int[] nth = new int[nums.length];
		int N = 0;
		while (N < nums.length) {
			nth[N] = (int) (Math.pow(nums[N], n));
			N++;
		}
		return nth;
	}
}
