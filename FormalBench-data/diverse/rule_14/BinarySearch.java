
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinarySearch {

	public static Boolean binarySearch(int[] itemArray, int item) {
		int low = 0;
		int high = itemArray.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (!(item == itemArray[mid])) {
				if (item < itemArray[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				return true;
			}
		}
		return false;
	}
}
