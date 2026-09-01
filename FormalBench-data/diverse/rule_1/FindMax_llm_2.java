
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMax {

	public static int findMax(int[] arr, int num, int high) {
		if (num == high) {
			return arr[num];
		}
		int i = (num + high) / 2;
		int end = findMax(arr, num, i);
		int right = findMax(arr, i + 1, high);
		int result = Math.max(end, right);
		return result;
	}
}
