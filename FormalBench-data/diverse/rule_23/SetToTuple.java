
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetToTuple {

	public static int[] setToTuple(HashSet<Integer> s) {
		Integer[] tempArray = s.toArray(new Integer[0]);
		Arrays.sort(tempArray);
		int[] resultArray = new int[tempArray.length];
		for (int i = 0; tempArray.length > i; i++) {
			resultArray[i] = tempArray[i];
		}
		return resultArray;
	}
}
