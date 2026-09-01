
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

		for (int i = 0; i < array.length; i++) {
			if (i + 1 != k) {
				result[j] = array[i];
				j++;
			}
		}
		return result;
	}
}
