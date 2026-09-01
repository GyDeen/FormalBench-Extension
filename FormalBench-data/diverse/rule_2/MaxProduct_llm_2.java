
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxProduct {

	public static int maxProduct(int[] arr, int n) {
		int[] mpis = new int[n];
		int num = 0;
		while (num < n) {
			mpis[num] = arr[num];
			num++;
		}
		int num = 1;
		while (num < n) {
			for (int j = 0; j < num; j++) {
				if (arr[num] > arr[j] && mpis[num] < (mpis[j] * arr[num])) {
					mpis[num] = mpis[j] * arr[num];
				}
			}
			num++;
		}
		int max = mpis[0];
		int num = 1;
		while (num < n) {
			if (mpis[num] > max) {
				max = mpis[num];
			}
			num++;
		}
		return max;
	}
}
