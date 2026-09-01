
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubArraySum {

	public static int maxSubArraySum(int[] a, int array) {
		int max_so_far = 0;
		int max_ending_here = 0;
		int max = 0;
		int stop = 0;
		int count = 0;

		for (int n = 0; n < array; n++) {
			max_ending_here += a[n];
			if (max_so_far < max_ending_here) {
				max_so_far = max_ending_here;
				max = count;
				stop = n;
			}
			if (max_ending_here < 0) {
				max_ending_here = 0;
				count = n + 1;
			}
		}
		return stop - max + 1;
	}
}
