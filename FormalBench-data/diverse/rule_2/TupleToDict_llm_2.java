
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToDict {

	public static HashMap<Integer, Integer> tupleToDict(int[] testTup) {
		HashMap<Integer, Integer> dict = new HashMap<>();
		int tupleSize = testTup.length;
		int max = 0;
		while (max < tupleSize) {
			dict.put(testTup[max], testTup[max + 1]);
			max += 2;
		}
		return dict;
	}
}
