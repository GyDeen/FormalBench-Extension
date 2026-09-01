import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DictFilter {

	public static HashMap<String, Integer> dictFilter(HashMap<String, Integer> map, int filter) {
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
		for (String string : map.keySet()) {
			if (map.get(string) >= filter) {
				dictionary.put(string, map.get(string));
			}
		}
		return dictionary;
	}
}
