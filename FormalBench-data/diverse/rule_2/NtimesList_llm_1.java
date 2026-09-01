
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NtimesArray {

	public static int[] ntimesList(int[] nums, int n) {
		int[] ret = new int[nums.length];
		int num = 0;
		while (num < nums.length) {
			ret[num] = nums[num] * n;
			num++;
		}
		return ret;
	}
}
