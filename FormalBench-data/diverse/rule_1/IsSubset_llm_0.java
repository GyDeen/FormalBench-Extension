
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubset {

	public static Boolean isSubset(int[] values, int m, int[] arr, int num) {
		HashSet<Integer> sets = new HashSet<>();
		for (int index = 0; index < m; index++) {
			sets.add(values[index]);
		}
		for (int i = 0; i < num; i++) {
			if (!sets.contains(arr[i])) {
				return false;
			}
		}
		return true;
	}
}
