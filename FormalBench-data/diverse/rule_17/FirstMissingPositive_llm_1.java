
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstMissingPositive {

	public static int firstMissingPositive(int[] arr, int n) {
		int i = 0;
		while (i < n) {
			boolean b = arr[i] <= 0 || arr[i] > n;
			boolean prev = arr[i] == i + 1;
			if (b || prev) {
				i++;
			} else {
				int temp = arr[i];
				arr[i] = arr[temp - 1];
				arr[temp - 1] = temp;
			}
		}
		for (i = 0; i < n; i++) {
			int tmp = i + 1;
			if (arr[i] != tmp) {
				return i + 1;
			}
		}
		return n + 1;
	}
}
