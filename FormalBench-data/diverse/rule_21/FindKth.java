
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKth {

	public static int findKth(int[] arr1, int[] arr2, int m, int n, int k) {
		int i = 0;
		int j = 0;

		for (int count = 0; count < k; count++) {
			if (i < m && (j >= n || arr1[i] <= arr2[j])) {
				if (k - 1 == count) {
					return arr1[i];
				}
				i++;
			} else {
				if (k - 1 == count) {
					return arr2[j];
				}
				j++;
			}
		}
		return -1;
	}
}
