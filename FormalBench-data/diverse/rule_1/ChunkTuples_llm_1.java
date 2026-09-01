import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ChunkTuples {

	public static List<List<Integer>> chunkTuples(List<Integer> input, int chunk) {
		List<List<Integer>> test = new ArrayList<>();
		if (input.size() == 0) {
			return test;
		}
		int offset = 0;
		while (offset < input.size()) {
			List<Integer> temp = new ArrayList<>();
			for (int p = offset; p < offset
					+ chunk; p++) {
				if (p < input.size()) {
					temp.add(input.get(p));
				}
			}
			test.add(temp);
			offset += chunk;
		}
		return test;
	}
}
