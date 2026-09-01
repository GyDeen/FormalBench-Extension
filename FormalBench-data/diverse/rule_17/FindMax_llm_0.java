
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMax {

	public static int findMax(int[] arr, int low, int high) {
		if (low == high) {
			return arr[low];
		}
		int i = low + high;
		int mid = (i) / 2;
		int leftMax = findMax(arr, low, mid);
		int rightMax = findMax(arr, mid + 1, high);
		int maxOfLeftAndRight = Math.max(leftMax, rightMax);
		return maxOfLeftAndRight;
	}
}
