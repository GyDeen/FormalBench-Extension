import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddDict {

	public static HashMap<String, Integer> addDict(HashMap<String, Integer> map,
			HashMap<String, Integer> d) {
		HashMap<String, Integer> a = new HashMap<>();
		for (String string : map.keySet()) {
			if (d.containsKey(string)) {
				a.put(string,
						map.get(string) + d.get(string));
			} else {
				a.put(string, map.get(string));
			}
		}
		for (String string : d.keySet()) {
			if (!map.containsKey(string)) {
				a.put(string, d.get(string));
			}
		}
		return a;
	}
}
