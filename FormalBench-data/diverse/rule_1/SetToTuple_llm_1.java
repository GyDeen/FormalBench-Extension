
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetToTuple {

	public static int[] setToTuple(HashSet<Integer> set) {
		Integer[] result = set.toArray(new Integer[0]);
		Arrays.sort(result);
		int[] temp = new int[result.length];
		for (int index = 0; index < result.length; index++) {
			temp[index] = result[index];
		}
		return temp;
	}
}
