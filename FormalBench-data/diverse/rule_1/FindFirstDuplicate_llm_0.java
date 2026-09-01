
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstDuplicate {

	public static int findFirstDuplicate(int[] arr) {
		Set<Integer> sets = new HashSet<>();
		for (int i : arr) {
			if (sets.contains(i)) {
				return i;
			}
			sets.add(i);
		}
		return -1;
	}
}
