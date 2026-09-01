
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Overlapping {

	public static Boolean overlapping(int[] arr, int[] array) {
		int result = 0;
		int count = 0;

		while (result < arr.length && count < array.length) {
			if (arr[result] == array[count]) {
				count++;
				if (count == array.length) {
					return true;
				}
			} else {
				count = 0;
			}
			result++;
		}
		return false;
	}
}
