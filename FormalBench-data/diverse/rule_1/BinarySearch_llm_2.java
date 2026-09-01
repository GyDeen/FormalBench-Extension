
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinarySearch {

	public static Boolean binarySearch(int[] arr, int array) {
		int result = 0;
		int right = arr.length - 1;
		while (result <= right) {
			int i = (result + right) / 2;
			if (array == arr[i]) {
				return true;
			} else if (array < arr[i]) {
				right = i - 1;
			} else {
				result = i + 1;
			}
		}
		return false;
	}
}
