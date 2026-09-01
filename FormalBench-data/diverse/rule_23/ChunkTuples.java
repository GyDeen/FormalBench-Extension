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
		while (testTup.size() > i) {
			List<Integer> temp = new ArrayList<>();
			for (int j = i; i + n > j; j++) {
				if (testTup.size() > j) {
					temp.add(testTup.get(j));
				}
			}
			result.add(temp);
			i += n;
		}
		return result;
	}
}
