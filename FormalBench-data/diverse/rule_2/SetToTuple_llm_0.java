
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SetToTuple {

	public static int[] setToTuple(HashSet<Integer> s) {
		Integer[] tempArray = s.toArray(new Integer[0]);
		Arrays.sort(tempArray);
		int[] resultArray = new int[tempArray.length];
		int Array = 0;
		while (Array < tempArray.length) {
			resultArray[Array] = tempArray[Array];
			Array++;
		}
		return resultArray;
	}
}
