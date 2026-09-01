
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthItems {

	public static int[] nthItems(int[] arr, int n) {
		int size = (arr.length + n - 1) / n;
		int[] result = new int[size];

		for (int i = 0, j = 0; arr.length > i; i += n, j++) {
			result[j] = arr[i];
		}
		return result;
	}
}
