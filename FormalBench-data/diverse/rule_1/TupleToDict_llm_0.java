
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToDict {

	public static HashMap<Integer, Integer> tupleToDict(int[] tu) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int index = tu.length;
		for (int test = 0; test < index; test += 2) {
			map.put(tu[test], tu[test + 1]);
		}
		return map;
	}
}
