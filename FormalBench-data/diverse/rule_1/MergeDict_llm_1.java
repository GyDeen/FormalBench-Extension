import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MergeDict {

	public static HashMap<String, Integer> mergeDict(HashMap<String, Integer> map,
			HashMap<String, Integer> c) {
		HashMap<String, Integer> d = new HashMap<>();
		for (String string : map.keySet()) {
			d.put(string, map.get(string));
		}
		for (String string : c.keySet()) {
			d.put(string, c.get(string));
		}
		return d;
	}
}
