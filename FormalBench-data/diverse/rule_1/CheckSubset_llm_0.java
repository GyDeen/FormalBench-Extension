
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSubset {

	public static Boolean checkSubset(int[] arr, int[] test) {
		Set<Integer> sets = new HashSet<>();
		for (int index = 0; index < arr.length; index++) {
			sets.add(arr[index]);
		}
		for (int index = 0; index < test.length; index++) {
			if (!sets.contains(test[index])) {
				return false;
			}
		}
		return true;
	}
}
