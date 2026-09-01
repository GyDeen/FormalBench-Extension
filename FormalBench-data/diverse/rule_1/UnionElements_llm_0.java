
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UnionElements {

	public static int[] unionElements(int[] testTup1, int[] test) {
		Set<Integer> set = new HashSet<>();

		for (int i : testTup1) {
			set.add(i);
		}

		for (int element : test) {
			set.add(element);
		}

		int[] union = new int[set.size()];
		int Array = 0;
		for (int element : set) {
			union[Array++] = element;
		}

		return union;
	}
}
