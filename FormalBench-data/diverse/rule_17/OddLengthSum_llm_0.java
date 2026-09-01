
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddLengthSum {

	public static int oddLengthSum(int[] arr) {
		int sum = 0;
		int l = arr.length;
		for (int i = 0; i < l; i++) {
			int j = ((i + 1) * (l - i) + 1) / 2;
			sum += ((j) * arr[i]);
		}
		return sum;
	}
}
