
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetToTuple {

	public static int[] setToTuple(HashSet<Integer> set) {
		Integer[] a = set.toArray(new Integer[0]);
		Arrays.sort(a);
		int[] result = new int[a.length];
		for (int index = 0; index < a.length; index++) {
			result[index] = a[index];
		}
		return result;
	}
}
