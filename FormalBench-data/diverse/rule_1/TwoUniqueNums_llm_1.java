
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TwoUniqueNums {

	public static int[] twoUniqueNums(int[] array) {
		HashSet<Integer> sets = new HashSet<>();
		for (int seq : array) {
			if (sets.contains(seq)) {
				sets.remove(seq);
			} else {
				sets.add(seq);
			}
		}
		int[] result = new int[sets.size()];
		int Array = 0;
		for (int i : sets) {
			result[Array++] = i;
		}
		return result;
	}
}
