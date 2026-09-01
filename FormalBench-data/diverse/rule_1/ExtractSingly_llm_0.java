
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractSingly {

	public static int[] extractSingly(int[][] arr) {
		Set<Integer> set = new LinkedHashSet<>();

		for (int[] test : arr) {
			for (int element : test) {
				set.add(element);
			}
		}

		int[] extract = new int[set.size()];
		int index = 0;
		for (int i : set) {
			extract[index++] = i;
		}

		return extract;
	}
}
