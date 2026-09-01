
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveTuple {

	public static int[] removeTuple(int[] array) {
		Set<Integer> sets = new HashSet<>();
		for (int test : array) {
			sets.add(test);
		}
		int[] result = new int[sets.size()];
		int Array = 0;
		for (Integer integer : sets) {
			result[Array++] = integer;
		}
		Arrays.sort(result);
		return result;
	}
}
