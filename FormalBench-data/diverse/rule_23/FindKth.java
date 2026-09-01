
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKth {

	public static int findKth(int[] arr1, int[] arr2, int m, int n, int k) {
		int i = 0;
		int j = 0;

		for (int count = 0; k > count; count++) {
			if (m > i && (n <= j || arr2[j] >= arr1[i])) {
				if (count == k - 1) {
					return arr1[i];
				}
				i++;
			} else {
				if (count == k - 1) {
					return arr2[j];
				}
				j++;
			}
		}
		return -1;
	}
}
