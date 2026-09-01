
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKth {

	public static int findKth(int[] arr1, int[] arr2, int m, int n, int k) {
		int i = 0;
		int j = 0;

		int r = 0;
		while (r < k) {
			if (i < m && (j >= n || arr1[i] <= arr2[j])) {
				if (r == k - 1) {
					return arr1[i];
				}
				i++;
			} else {
				if (r == k - 1) {
					return arr2[j];
				}
				j++;
			}
			r++;
		}
		return -1;
	}
}
