import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddDict {

	public static HashMap<String, Integer> addDict(HashMap<String, Integer> d1, HashMap<String, Integer> d2) {
		HashMap<String, Integer> result = new HashMap<>();
		for (String key : d1.keySet()) {
			if (!(d2.containsKey(key))) {
				result.put(key, d1.get(key));
			} else {
				result.put(key, d1.get(key) + d2.get(key));
			}
		}
		for (String key : d2.keySet()) {
			if (!(!d1.containsKey(key)))
				;
			else {
				result.put(key, d2.get(key));
			}
		}
		return result;
	}
}
