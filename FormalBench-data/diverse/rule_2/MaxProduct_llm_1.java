
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProduct {

	public static int maxProduct(int[] arr, int n) {
		int[] mpis = new int[n];
		int Array = 0;
		while (Array < n) {
			mpis[Array] = arr[Array];
			Array++;
		}
		int Array = 1;
		while (Array < n) {
			for (int j = 0; j < Array; j++) {
				if (arr[Array] > arr[j] && mpis[Array] < (mpis[j] * arr[Array])) {
					mpis[Array] = mpis[j] * arr[Array];
				}
			}
			Array++;
		}
		int max = mpis[0];
		int Array = 1;
		while (Array < n) {
			if (mpis[Array] > max) {
				max = mpis[Array];
			}
			Array++;
		}
		return max;
	}
}
