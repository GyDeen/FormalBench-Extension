
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxOccurrences {

	public static int[] maxOccurrences(int[] nums) {
		int maxOccurrences = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		Integer maxOccurrence = null;

		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
			if (map.get(num) > maxOccurrences) {
				maxOccurrences = map.get(num);
				maxOccurrence = num;
			}
		}

		return new int[] { maxOccurrence, maxOccurrences };
	}
}
