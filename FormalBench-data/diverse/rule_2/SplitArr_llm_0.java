
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitArr {

	public static int[] splitArr(int[] a, int n, int k) {
		int[] result = new int[n];
		int Array = k;
		while (Array < n) {
			result[Array - k] = a[Array];
			Array++;
		}
		int Array = 0;
		while (Array < k) {
			result[n - k + Array] = a[Array];
			Array++;
		}
		return result;
	}
}
