
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NtimesArray {

	public static int[] ntimesList(int[] nums, int n) {
		int[] ret = new int[nums.length];
		int arr = 0;
		while (arr < nums.length) {
			ret[arr] = nums[arr] * n;
			arr++;
		}
		return ret;
	}
}
