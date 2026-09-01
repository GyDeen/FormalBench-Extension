
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetToTuple {

	public static int[] setToTuple(HashSet<Integer> set) {
		Integer[] ret = set.toArray(new Integer[0]);
		Arrays.sort(ret);
		int[] result = new int[ret.length];
		for (int index = 0; index < ret.length; index++) {
			result[index] = ret[index];
		}
		return result;
	}
}
