
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMax {

	public static int findMax(int[] arr, int low, int high) {
		if (low == high) {
			return arr[low];
		}
		int mid = (low + high) / 2;
		int leftMax = findMax(arr, low, mid), rightMax = findMax(arr, mid + 1, high);
		int maxOfLeftAndRight = Math.max(leftMax, rightMax);
		return maxOfLeftAndRight;
	}
}
