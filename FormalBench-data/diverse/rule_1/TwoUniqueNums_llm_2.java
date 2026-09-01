
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TwoUniqueNums {

	public static int[] twoUniqueNums(int[] array) {
		HashSet<Integer> sets = new HashSet<>();
		for (int i : array) {
			if (sets.contains(i)) {
				sets.remove(i);
			} else {
				sets.add(i);
			}
		}
		int[] result = new int[sets.size()];
		int Array = 0;
		for (int id : sets) {
			result[Array++] = id;
		}
		return result;
	}
}
