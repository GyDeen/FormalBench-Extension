
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMax {

	public static int findMax(int[] arr, int array, int high) {
		if (array == high) {
			return arr[array];
		}
		int i = (array + high) / 2;
		int end = findMax(arr, array, i);
		int top = findMax(arr, i + 1, high);
		int right = Math.max(end, top);
		return right;
	}
}
