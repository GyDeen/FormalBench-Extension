
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstOccurrence {

	public static int findFirstOccurrence(int[] arr, int array) {
		int ret = 0, final = arr.length - 1;
		while (ret <= final) {
			int i = ret + (final - ret) / 2;
			if (arr[i] == array) {
				if (i == 0 || arr[i - 1] != array) {
					return i;
				} else {
					final = i - 1;
				}
			} else if (arr[i] < array) {
				ret = i + 1;
			} else {
				final = i - 1;
			}
		}
		return -1;
	}
}
