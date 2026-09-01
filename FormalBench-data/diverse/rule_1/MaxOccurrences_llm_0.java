
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxOccurrences {

	public static int[] maxOccurrences(int[] array) {
		HashMap<Integer, Integer> result = new HashMap<>();
		int arr = 0;
		Integer number = null;

		for (int index : array) {
			result.put(index, result.getOrDefault(index, 0) + 1);
			if (result.get(index) > arr) {
				arr = result.get(index);
				number = index;
			}
		}

		return new int[] { number, arr };
	}
}
