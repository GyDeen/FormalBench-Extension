
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubset {

	public static Boolean isSubset(int[] values, int m, int[] array, int num) {
		HashSet<Integer> arr = new HashSet<>();
		for (int index = 0; index < m; index++) {
			arr.add(values[index]);
		}
		for (int i = 0; i < num; i++) {
			if (!arr.contains(array[i])) {
				return false;
			}
		}
		return true;
	}
}
