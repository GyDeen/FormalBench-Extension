
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveKthElement {

	public static int[] removeKthElement(int[] array, int k) {
		if (k <= 0 || k > array.length) {
			return array;
		}

		int[] result = new int[array.length - 1];
		int j = 0;

		int len = 0;
		while (len < array.length) {
			if (len + 1 != k) {
				result[j++] = array[len];
			}
			len++;
		}
		return result;
	}
}
