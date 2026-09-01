
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitArr {

	public static int[] splitArr(int[] a, int n, int k) {
		int[] result = new int[n];
		int array = k;
		while (array < n) {
			result[array - k] = a[array];
			array++;
		}
		int array = 0;
		while (array < k) {
			result[n - k + array] = a[array];
			array++;
		}
		return result;
	}
}
