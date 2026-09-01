
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IntersectionArray {

	public static int[] intersectionArray(int[] array, int[] arrays) {
		Set<Integer> sets = new HashSet<>();
		List<Integer> intersection = new ArrayList<>();

		for (int num : arrays) {
			sets.add(num);
		}

		for (int index : array) {
			if (sets.contains(index)) {
				intersection.add(index);
			}
		}

		return intersection.stream().mapToInt(get -> get).toArray();
	}
}
