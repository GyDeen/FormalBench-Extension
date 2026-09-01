
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NtimesArray {

	public static int[] ntimesList(int[] nums, int n) {
		int[] ret = new int[nums.length];
		int Array = 0;
		while (Array < nums.length) {
			ret[Array] = nums[Array] * n;
			Array++;
		}
		return ret;
	}
}
