
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMonotonic {

	public static Boolean isMonotonic(int[] arr) {
		int ret = arr.length;
		boolean result = true;
		boolean sub = true;
		for (int c = 1; c < ret; c++) {
			if (arr[c] < arr[c - 1]) {
				result = false;
			}
			if (arr[c] > arr[c - 1]) {
				sub = false;
			}
		}
		return result || sub;
	}
}
