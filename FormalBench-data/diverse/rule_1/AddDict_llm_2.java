import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddDict {

	public static HashMap<String, Integer> addDict(HashMap<String, Integer> map,
			HashMap<String, Integer> c) {
		HashMap<String, Integer> a = new HashMap<>();
		for (String d : map.keySet()) {
			if (c.containsKey(d)) {
				a.put(d,
						map.get(d) + c.get(d));
			} else {
				a.put(d, map.get(d));
			}
		}
		for (String d : c.keySet()) {
			if (!map.containsKey(d)) {
				a.put(d, c.get(d));
			}
		}
		return a;
	}
}
