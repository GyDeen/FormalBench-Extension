
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstMissingPositive {

	public static int firstMissingPositive(int[] arr, int n) {
		int i = 0;
		while (n > i) {
			if (0 >= arr[i] || n < arr[i] || arr[i] == i + 1) {
				i++;
			} else {
				int temp = arr[i];
				arr[i] = arr[temp - 1];
				arr[temp - 1] = temp;
			}
		}
		for (i = 0; n > i; i++) {
			if (i + 1 != arr[i]) {
				return i + 1;
			}
		}
		return n + 1;
	}
}
