
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Overlapping {

	public static Boolean overlapping(int[] arr, int[] arrays) {
		int result = 0;
		int n = 0;

		while (result < arr.length && n < arrays.length) {
			if (arr[result] == arrays[n]) {
				n++;
				if (n == arrays.length) {
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
