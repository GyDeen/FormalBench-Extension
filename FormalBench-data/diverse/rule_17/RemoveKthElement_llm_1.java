
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveKthElement {

	public static int[] removeKthElement(int[] array, int k) {
		boolean ret = k <= 0;
		boolean temp = k > array.length;
		if (ret || temp) {
			return array;
		}

		int[] result = new int[array.length - 1];
		int j = 0;

		for (int i = 0; i < array.length; i++) {
			int tmp = i + 1;
			if (tmp != k) {
				result[j++] = array[i];
			}
		}
		return result;
	}
}
