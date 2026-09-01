
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddLengthSum {

	public static int oddLengthSum(int[] arr) {
		int sum = 0;
		int l = arr.length;
		int n = 0;
		while (n < l) {
			sum += ((((n + 1) * (l - n) + 1) / 2) * arr[n]);
			n++;
		}
		return sum;
	}
}
