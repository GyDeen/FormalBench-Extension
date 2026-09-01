import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MergeDict {

	public static HashMap<String, Integer> mergeDict(HashMap<String, Integer> map,
			HashMap<String, Integer> d) {
		HashMap<String, Integer> r = new HashMap<>();
		for (String string : map.keySet()) {
			r.put(string, map.get(string));
		}
		for (String string : d.keySet()) {
			r.put(string, d.get(string));
		}
		return r;
	}
}
