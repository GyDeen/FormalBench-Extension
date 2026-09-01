import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MergeDict {

	public static HashMap<String, Integer> mergeDict(HashMap<String, Integer> merge,
			HashMap<String, Integer> d) {
		HashMap<String, Integer> r = new HashMap<>();
		for (String string : merge.keySet()) {
			r.put(string, merge.get(string));
		}
		for (String string : d.keySet()) {
			r.put(string, d.get(string));
		}
		return r;
	}
}
