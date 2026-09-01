
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKth {

	public static int findKth(int[] arr1, int[] arr, int Array, int n, int p) {
		int result = 0;
		int s = 0;

		for (int count = 0; count < p; count++) {
			if (result < Array
					&& (s >= n || arr1[result] <= arr[s])) {
				if (count == p - 1) {
					return arr1[result];
				}
				result++;
			} else {
				if (count == p - 1) {
					return arr[s];
				}
				s++;
			}
		}
		return -1;
	}
}
