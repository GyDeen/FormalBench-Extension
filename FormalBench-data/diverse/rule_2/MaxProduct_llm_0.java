
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProduct {

	public static int maxProduct(int[] arr, int n) {
		int[] mpis = new int[n];
		int array = 0;
		while (array < n) {
			mpis[array] = arr[array];
			array++;
		}
		int array = 1;
		while (array < n) {
			for (int j = 0; j < array; j++) {
				if (arr[array] > arr[j] && mpis[array] < (mpis[j] * arr[array])) {
					mpis[array] = mpis[j] * arr[array];
				}
			}
			array++;
		}
		int max = mpis[0];
		int array = 1;
		while (array < n) {
			if (mpis[array] > max) {
				max = mpis[array];
			}
			array++;
		}
		return max;
	}
}
