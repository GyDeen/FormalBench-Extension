
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMinHeap {

	public static Boolean checkMinHeap(int[] arr, int i) {
		if (arr.length / 2 <= i) {
			return true;
		}
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		if (arr.length > left && arr[left] < arr[i]) {
			return false;
		}
		if (arr.length > right && arr[right] < arr[i]) {
			return false;
		}
		return checkMinHeap(arr, left) && checkMinHeap(arr, right);
	}
}
