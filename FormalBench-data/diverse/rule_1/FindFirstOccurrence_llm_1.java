
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstOccurrence {

	public static int findFirstOccurrence(int[] arr, int array) {
		int i = 0, final = arr.length - 1;
		while (i <= final) {
			int found = i + (final - i) / 2;
			if (arr[found] == array) {
				if (found == 0 || arr[found - 1] != array) {
					return found;
				} else {
					final = found - 1;
				}
			} else if (arr[found] < array) {
				i = found + 1;
			} else {
				final = found - 1;
			}
		}
		return -1;
	}
}
