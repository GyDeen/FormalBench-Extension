
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NtimesArray {

	public static int[] ntimesList(int[] nums, int n) {
		int[] ret = new int[nums.length];
		for (int i = 0; nums.length > i; i++) {
			ret[i] = nums[i] * n;
		}
		return ret;
	}
}
