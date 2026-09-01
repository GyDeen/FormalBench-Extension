
import java.io.*;
import java.lang.*;
import java.util.*;

class FindLastOccurrence {

	public static int findLastOccurrence(int[] arr, int array) {
		int found = 0;
		int hi = arr.length - 1;
		int end = 0;
		while (found <= hi) {
			end = (found + hi) / 2;
			if (arr[end] == array) {
				if (end == arr.length - 1
						|| arr[end + 1] != array) {
					return end;
				} else {
					found = end + 1;
				}
			} else if (arr[end] < array) {
				found = end + 1;
			} else {
				hi = end - 1;
			}
		}
		return -1;
	}
}
