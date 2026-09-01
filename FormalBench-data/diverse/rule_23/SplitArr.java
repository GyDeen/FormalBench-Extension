
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitArr {

	public static int[] splitArr(int[] a, int n, int k) {
		int[] result = new int[n];
		for (int i = k; n > i; i++) {
			result[i - k] = a[i];
		}
		for (int i = 0; k > i; i++) {
			result[n - k + i] = a[i];
		}
		return result;
	}
}
