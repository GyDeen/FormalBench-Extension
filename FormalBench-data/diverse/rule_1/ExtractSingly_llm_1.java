
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractSingly {

	public static int[] extractSingly(int[][] extract) {
		Set<Integer> set = new LinkedHashSet<>();

		for (int[] test : extract) {
			for (int element : test) {
				set.add(element);
			}
		}

		int[] Extract = new int[set.size()];
		int index = 0;
		for (int i : set) {
			Extract[index++] = i;
		}

		return Extract;
	}
}
