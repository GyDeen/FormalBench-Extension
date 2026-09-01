
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinarySearch {

	public static Boolean binarySearch(int[] itemArray, int item) {
		int low = 0;
		int high = itemArray.length - 1;
		while (low <= high) {
			int i = low + high;
			int mid = (i) / 2;
			if (item == itemArray[mid]) {
				return true;
			} else if (item < itemArray[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return false;
	}
}
