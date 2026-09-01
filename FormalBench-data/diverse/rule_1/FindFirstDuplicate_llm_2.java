
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstDuplicate {

	public static int findFirstDuplicate(int[] arr) {
		Set<Integer> set = new HashSet<>();
		for (int i : arr) {
			if (set.contains(i)) {
				return i;
			}
			set.add(i);
		}
		return -1;
	}
}
