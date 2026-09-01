
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstOccurrence {

	public static int findFirstOccurrence(int[] arr, int array) {
		int result = 0, final = arr.length - 1;
		while (result <= final) {
			int i = result + (final - result) / 2;
			if (arr[i] == array) {
				if (i == 0 || arr[i - 1] != array) {
					return i;
				} else {
					final = i - 1;
				}
			} else if (arr[i] < array) {
				result = i + 1;
			} else {
				final = i - 1;
			}
		}
		return -1;
	}
}
