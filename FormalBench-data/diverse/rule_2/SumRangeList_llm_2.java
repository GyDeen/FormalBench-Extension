
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumRangeArray {

	public static int sumRangeArray(int[] nums, int m, int n) {
		int sum = 0;
		int max = m;
		while (max <= n) {
			sum += nums[max];
			max++;
		}
		return sum;
	}
}
