
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRotation {

	public static int countRotation(int[] arr, int n) {
		if (0 == n) {
			return 0;
		}
		int left = 0, right = n - 1;
		while (left < right) {
			if (arr[left] < arr[right]) {
				return left + 1;
			}
			int mid = (left + right) / 2;
			if (arr[mid] > arr[left]) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return left + 1;
	}
}
