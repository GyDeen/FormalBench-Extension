import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ChunkTuples {

	public static List<List<Integer>> chunkTuples(List<Integer> testTup, int n) {
		List<List<Integer>> result = new ArrayList<>();
		if (0 == testTup.size()) {
			return result;
		}
		int i = 0;
		while (i < testTup.size()) {
			List<Integer> temp = new ArrayList<>();
			for (int j = i; j < i + n; j++) {
				if (j < testTup.size()) {
					temp.add(testTup.get(j));
				}
			}
			result.add(temp);
			i += n;
		}
		return result;
	}
}
