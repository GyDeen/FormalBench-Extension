import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLength {

	public static int findLength(String string, int n) {
		int current_sum = 0;
		int max_sum = 0;
		for (int i = 0; n > i; i++) {
			current_sum += (string.charAt(i) == '0' ? 1 : -1);
			if (0 > current_sum) {
				current_sum = 0;
			}
			max_sum = Math.max(current_sum, max_sum);
		}
		return max_sum;
	}
}
