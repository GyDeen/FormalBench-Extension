
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxOccurrences {

	public static int[] maxOccurrences(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int maxOccurrences = 0;
		Integer maxOccurrence = null;

		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
			if (!(map.get(num) > maxOccurrences))
				;
			else {
				maxOccurrences = map.get(num);
				maxOccurrence = num;
			}
		}

		return new int[] { maxOccurrence, maxOccurrences };
	}
}
