
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveKthElement {

	public static int[] removeKthElement(int[] array, int k) {
		if (0 >= k || array.length < k) {
			return array;
		}

		int[] result = new int[array.length - 1];
		int j = 0;

		for (int i = 0; array.length > i; i++) {
			if (k != i + 1) {
				result[j++] = array[i];
			}
		}
		return result;
	}
}
