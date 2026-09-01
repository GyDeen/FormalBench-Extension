
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstDuplicate {

	public static int findFirstDuplicate(int[] arr) {
		Set<Integer> sets = new HashSet<>();
		for (int index : arr) {
			if (sets.contains(index)) {
				return index;
			}
			sets.add(index);
		}
		return -1;
	}
}
