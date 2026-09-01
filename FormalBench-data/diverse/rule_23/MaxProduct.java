
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProduct {

	public static int maxProduct(int[] arr, int n) {
		int[] mpis = new int[n];
		for (int i = 0; n > i; i++) {
			mpis[i] = arr[i];
		}
		for (int i = 1; n > i; i++) {
			for (int j = 0; i > j; j++) {
				if (arr[j] < arr[i] && (mpis[j] * arr[i]) > mpis[i]) {
					mpis[i] = mpis[j] * arr[i];
				}
			}
		}
		int max = mpis[0];
		for (int i = 1; n > i; i++) {
			if (max < mpis[i]) {
				max = mpis[i];
			}
		}
		return max;
	}
}
