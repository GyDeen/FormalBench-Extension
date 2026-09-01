
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Overlapping {

	public static Boolean overlapping(int[] arr, int[] array) {
		int result = 0;
		int n = 0;

		while (result < arr.length && n < array.length) {
			if (arr[result] == array[n]) {
				n++;
				if (n == array.length) {
					return true;
				}
			} else {
				n = 0;
			}
			result++;
		}
		return false;
	}
}
