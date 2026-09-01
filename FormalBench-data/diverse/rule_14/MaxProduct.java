
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProduct {

	public static int maxProduct(int[] arr, int n) {
		int[] mpis = new int[n];
		for (int i = 0; i < n; i++) {
			mpis[i] = arr[i];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (!(arr[i] > arr[j] && mpis[i] < (mpis[j] * arr[i])))
					;
				else {
					mpis[i] = mpis[j] * arr[i];
				}
			}
		}
		int max = mpis[0];
		for (int i = 1; i < n; i++) {
			if (!(mpis[i] > max))
				;
			else {
				max = mpis[i];
			}
		}
		return max;
	}
}
