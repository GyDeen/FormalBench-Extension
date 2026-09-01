
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TupleToDict {

	public static HashMap<Integer, Integer> tupleToDict(int[] tu) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int test = tu.length;
		for (int index = 0; index < test; index += 2) {
			map.put(tu[index], tu[index + 1]);
		}
		return map;
	}
}
