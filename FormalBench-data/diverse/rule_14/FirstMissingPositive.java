
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstMissingPositive {

	public static int firstMissingPositive(int[] arr, int n) {
		int i = 0;
		while (i < n) {
			if (!(arr[i] <= 0 || arr[i] > n || arr[i] == i + 1)) {
				int temp = arr[i];
				arr[i] = arr[temp - 1];
				arr[temp - 1] = temp;
			} else {
				i++;
			}
		}
		for (i = 0; i < n; i++) {
			if (!(arr[i] != i + 1))
				;
			else {
				return i + 1;
			}
		}
		return n + 1;
	}
}
