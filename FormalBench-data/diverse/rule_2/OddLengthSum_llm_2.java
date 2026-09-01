
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddLengthSum {

	public static int oddLengthSum(int[] arr) {
		int sum = 0;
		int l = arr.length;
		int d = 0;
		while (d < l) {
			sum += ((((d + 1) * (l - d) + 1) / 2) * arr[d]);
			d++;
		}
		return sum;
	}
}
