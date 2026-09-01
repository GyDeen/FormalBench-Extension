
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxOccurrences {

	public static int[] maxOccurrences(int[] array) {
		HashMap<Integer, Integer> result = new HashMap<>();
		int arr = 0;
		Integer number = null;

		for (int i : array) {
			result.put(i, result.getOrDefault(i, 0) + 1);
			if (result.get(i) > arr) {
				arr = result.get(i);
				number = i;
			}
		}

		return new int[] { number, arr };
	}
}
