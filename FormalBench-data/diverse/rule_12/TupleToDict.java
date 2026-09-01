
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToDict {

	public static HashMap<Integer, Integer> tupleToDict(int[] testTup) {
		int tupleSize = testTup.length;
		HashMap<Integer, Integer> dict = new HashMap<>();
		for (int i = 0; i < tupleSize; i += 2) {
			dict.put(testTup[i], testTup[i + 1]);
		}
		return dict;
	}
}
