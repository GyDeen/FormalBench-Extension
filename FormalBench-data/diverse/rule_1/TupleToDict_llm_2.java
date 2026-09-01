
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToDict {

	public static HashMap<Integer, Integer> tupleToDict(int[] tu) {
		HashMap<Integer, Integer> test = new HashMap<>();
		int ret = tu.length;
		for (int index = 0; index < ret; index += 2) {
			test.put(tu[index], tu[index + 1]);
		}
		return test;
	}
}
