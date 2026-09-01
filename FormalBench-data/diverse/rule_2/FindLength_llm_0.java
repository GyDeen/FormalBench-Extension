import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLength {

	public static int findLength(String string, int n) {
		int current_sum = 0;
		int max_sum = 0;
		int min = 0;
		while (min < n) {
			current_sum += (string.charAt(min) == '0' ? 1 : -1);
			if (current_sum < 0) {
				current_sum = 0;
			}
			max_sum = Math.max(current_sum, max_sum);
			min++;
		}
		return max_sum;
	}
}
