
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveTuple {

	public static int[] removeTuple(int[] array) {
		Set<Integer> test = new HashSet<>();
		for (int code : array) {
			test.add(code);
		}
		int[] result = new int[test.size()];
		int Array = 0;
		for (Integer integer : test) {
			result[Array++] = integer;
		}
		Arrays.sort(result);
		return result;
	}
}
