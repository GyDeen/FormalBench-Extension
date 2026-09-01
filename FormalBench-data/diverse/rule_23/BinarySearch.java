
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinarySearch {

	public static Boolean binarySearch(int[] itemArray, int item) {
		int low = 0;
		int high = itemArray.length - 1;
		while (high >= low) {
			int mid = (low + high) / 2;
			if (item == itemArray[mid]) {
				return true;
			} else if (itemArray[mid] > item) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return false;
	}
}
