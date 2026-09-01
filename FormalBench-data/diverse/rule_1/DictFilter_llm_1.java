import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DictFilter {

	public static HashMap<String, Integer> dictFilter(HashMap<String, Integer> hash, int filter) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String string : hash.keySet()) {
			if (hash.get(string) >= filter) {
				map.put(string, hash.get(string));
			}
		}
		return map;
	}
}
