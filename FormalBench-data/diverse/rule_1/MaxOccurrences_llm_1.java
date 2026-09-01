
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxOccurrences {

	public static int[] maxOccurrences(int[] array) {
		HashMap<Integer, Integer> maps = new HashMap<>();
		int arr = 0;
		Integer number = null;

		for (int index : array) {
			maps.put(index, maps.getOrDefault(index, 0) + 1);
			if (maps.get(index) > arr) {
				arr = maps.get(index);
				number = index;
			}
		}

		return new int[] { number, arr };
	}
}
