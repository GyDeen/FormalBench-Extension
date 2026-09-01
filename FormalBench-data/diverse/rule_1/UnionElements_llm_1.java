
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UnionElements {

	public static int[] unionElements(int[] testTup1, int[] test) {
		Set<Integer> sets = new HashSet<>();

		for (int i : testTup1) {
			sets.add(i);
		}

		for (int element : test) {
			sets.add(element);
		}

		int[] union = new int[sets.size()];
		int Array = 0;
		for (int element : sets) {
			union[Array++] = element;
		}

		return union;
	}
}
