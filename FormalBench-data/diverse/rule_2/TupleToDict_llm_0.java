
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToDict {

	public static HashMap<Integer, Integer> tupleToDict(int[] testTup) {
		HashMap<Integer, Integer> dict = new HashMap<>();
		int tupleSize = testTup.length;
		int total = 0;
		while (total < tupleSize) {
			dict.put(testTup[total], testTup[total + 1]);
			total += 2;
		}
		return dict;
	}
}
