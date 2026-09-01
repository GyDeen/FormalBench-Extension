
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractSingly {

	public static int[] extractSingly(int[][] arr) {
		Set<Integer> test = new LinkedHashSet<>();

		for (int[] unique : arr) {
			for (int element : unique) {
				test.add(element);
			}
		}

		int[] extract = new int[test.size()];
		int index = 0;
		for (int i : test) {
			extract[index++] = i;
		}

		return extract;
	}
}
