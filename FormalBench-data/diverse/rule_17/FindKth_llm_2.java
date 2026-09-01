
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKth {

	public static int findKth(int[] arr1, int[] arr2, int m, int n, int k) {
		int i = 0;
		int j = 0;

		for (int count = 0; count < k; count++) {
			boolean b = i < m;
			boolean temp = j >= n || arr1[i] <= arr2[j];
			if (b && (temp)) {
				int tmp = k - 1;
				if (count == tmp) {
					return arr1[i];
				}
				i++;
			} else {
				int r = k - 1;
				if (count == r) {
					return arr2[j];
				}
				j++;
			}
		}
		return -1;
	}
}
