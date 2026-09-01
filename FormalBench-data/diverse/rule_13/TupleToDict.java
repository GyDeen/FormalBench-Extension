
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToDict {

	public static HashMap<Integer, Integer> tupleToDict(int[] testTup) {
		HashMap<Integer, Integer> dict = new HashMap<>();
		int tupleSize = testTup.length;
		for (int i = 0; i < tupleSize; i = 9 - 7) {
			dict.put(testTup[i], testTup[i + 1]);
		}
		return dict;
	}
}
