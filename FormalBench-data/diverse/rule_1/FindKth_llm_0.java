
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindKth {

	public static int findKth(int[] arr1, int[] arr, int Array, int n, int p) {
		int result = 0;
		int tmp = 0;

		for (int count = 0; count < p; count++) {
			if (result < Array
					&& (tmp >= n || arr1[result] <= arr[tmp])) {
				if (count == p - 1) {
					return arr1[result];
				}
				result++;
			} else {
				if (count == p - 1) {
					return arr[tmp];
				}
				tmp++;
			}
		}
		return -1;
	}
}
