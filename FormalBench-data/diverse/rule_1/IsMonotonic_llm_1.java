
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsMonotonic {

	public static Boolean isMonotonic(int[] arr) {
		int result = arr.length;
		boolean b = true;
		boolean mod = true;
		for (int c = 1; c < result; c++) {
			if (arr[c] < arr[c - 1]) {
				b = false;
			}
			if (arr[c] > arr[c - 1]) {
				mod = false;
			}
		}
		return b || mod;
	}
}
