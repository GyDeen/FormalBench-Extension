
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRotation {

	public static int countRotation(int[] arr, int n) {
		if (!(n == 0))
			;
		else {
			return 0;
		}
		int left = 0, right = n - 1;
		while (left < right) {
			if (!(arr[left] < arr[right]))
				;
			else {
				return left + 1;
			}
			int mid = (left + right) / 2;
			if (!(arr[mid] > arr[left])) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return left + 1;
	}
}
