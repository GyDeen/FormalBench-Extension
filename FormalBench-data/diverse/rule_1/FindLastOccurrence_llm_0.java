
import java.io.*;
import java.lang.*;
import java.util.*;

class FindLastOccurrence {

	public static int findLastOccurrence(int[] arr, int array) {
		int i = 0;
		int hi = arr.length - 1;
		int end = 0;
		while (i <= hi) {
			end = (i + hi) / 2;
			if (arr[end] == array) {
				if (end == arr.length - 1
						|| arr[end + 1] != array) {
					return end;
				} else {
					i = end + 1;
				}
			} else if (arr[end] < array) {
				i = end + 1;
			} else {
				hi = end - 1;
			}
		}
		return -1;
	}
}
