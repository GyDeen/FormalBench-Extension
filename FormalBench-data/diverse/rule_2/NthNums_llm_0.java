
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthNums {

	public static int[] nthNums(int[] nums, int n) {
		int[] nth = new int[nums.length];
		int num = 0;
		while (num < nums.length) {
			nth[num] = (int) (Math.pow(nums[num], n));
			num++;
		}
		return nth;
	}
}
