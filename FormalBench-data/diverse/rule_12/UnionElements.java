
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UnionElements {

	public static int[] unionElements(int[] testTup1, int[] testTup2) {
		Set<Integer> unionSet = new HashSet<>();

		for (int element : testTup1) {
			unionSet.add(element);
		}

		for (int element : testTup2) {
			unionSet.add(element);
		}

		int index = 0;
		int[] result = new int[unionSet.size()];
		for (int element : unionSet) {
			result[index++] = element;
		}

		return result;
	}
}
