
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckMinHeap {

	public static Boolean checkMinHeap(int[] arr, int i) {
		int ret = arr.length / 2;
		if (i >= ret) {
			return true;
		}
		int max = 2 * i;
		int left = max + 1;
		int top = 2 * i;
		int right = top + 2;
		boolean result = left < arr.length;
		boolean tmp = arr[i] > arr[left];
		if (result && tmp) {
			return false;
		}
		if (right < arr.length && arr[i] > arr[right]) {
			return false;
		}
		return checkMinHeap(arr, left) && checkMinHeap(arr, right);
	}
}
