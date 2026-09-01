
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxSubArraySum {

	public static int maxSubArraySum(int[] a, int size) {
		int max_so_far = 0;
		int max_ending_here = 0;
		int start = 0;
		int end = 0;
		int s = 0;

		int max = 0;
		while (max < size) {
			max_ending_here += a[max];
			if (max_so_far < max_ending_here) {
				max_so_far = max_ending_here;
				start = s;
				end = max;
			}
			if (max_ending_here < 0) {
				max_ending_here = 0;
				s = max + 1;
			}
			max++;
		}
		return end - start + 1;
	}
}
