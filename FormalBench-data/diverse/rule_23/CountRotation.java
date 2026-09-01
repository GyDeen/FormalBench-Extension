
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRotation {

	public static int countRotation(int[] arr, int n) {
		if (n == 0) {
			return 0;
		}
		int left = 0, right = n - 1;
		while (right > left) {
			if (arr[right] > arr[left]) {
				return left + 1;
			}
			int mid = (left + right) / 2;
			if (arr[left] < arr[mid]) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return left + 1;
	}
}
