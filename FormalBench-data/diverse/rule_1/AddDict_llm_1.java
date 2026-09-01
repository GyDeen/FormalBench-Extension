import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddDict {

	public static HashMap<String, Integer> addDict(HashMap<String, Integer> map,
			HashMap<String, Integer> c) {
		HashMap<String, Integer> d = new HashMap<>();
		for (String string : map.keySet()) {
			if (c.containsKey(string)) {
				d.put(string,
						map.get(string) + c.get(string));
			} else {
				d.put(string, map.get(string));
			}
		}
		for (String string : c.keySet()) {
			if (!map.containsKey(string)) {
				d.put(string, c.get(string));
			}
		}
		return d;
	}
}
