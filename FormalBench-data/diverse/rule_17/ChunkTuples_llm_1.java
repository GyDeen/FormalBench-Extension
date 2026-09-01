import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ChunkTuples {

	public static List<List<Integer>> chunkTuples(List<Integer> testTup, int n) {
		List<List<Integer>> result = new ArrayList<>();
		if (testTup.size() == 0) {
			return result;
		}
		int i = 0;
		while (i < testTup.size()) {
			List<Integer> temp = new ArrayList<>();
			int count = i + n;
			for (int j = i; j < count; j++) {
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
