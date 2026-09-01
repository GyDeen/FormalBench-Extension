import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CatalanNumber {

	public static int catalanNumber(int n) {
		int[] arr = new int[n + 1];
		arr[0] = 1;
		for (int c = 1; c <= n; c++) {
			arr[c] = 0;
			for (int k = 0; k < c; k++) {
				arr[c] += arr[k]
						* arr[c - k - 1];
			}
		}
		return arr[n];
	}
}
