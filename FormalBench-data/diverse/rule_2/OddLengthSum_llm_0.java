
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddLengthSum {

	public static int oddLengthSum(int[] arr) {
		int sum = 0;
		int l = arr.length;
		int r = 0;
		while (r < l) {
			sum += ((((r + 1) * (l - r) + 1) / 2) * arr[r]);
			r++;
		}
		return sum;
	}
}
