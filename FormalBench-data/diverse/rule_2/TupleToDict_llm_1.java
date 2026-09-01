
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToDict {

	public static HashMap<Integer, Integer> tupleToDict(int[] testTup) {
		HashMap<Integer, Integer> dict = new HashMap<>();
		int tupleSize = testTup.length;
		int num = 0;
		while (num < tupleSize) {
			dict.put(testTup[num], testTup[num + 1]);
			num += 2;
		}
		return dict;
	}
}
