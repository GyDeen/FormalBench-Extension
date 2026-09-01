
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSubset {

	public static Boolean checkSubset(int[] arr, int[] tests) {
		Set<Integer> sets = new HashSet<>();
		for (int index = 0; index < arr.length; index++) {
			sets.add(arr[index]);
		}
		for (int index = 0; index < tests.length; index++) {
			if (!sets.contains(tests[index])) {
				return false;
			}
		}
		return true;
	}
}
