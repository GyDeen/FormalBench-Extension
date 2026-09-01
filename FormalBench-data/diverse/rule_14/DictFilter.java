import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DictFilter {

	public static HashMap<String, Integer> dictFilter(HashMap<String, Integer> dict, int n) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		for (String s : dict.keySet()) {
			if (!(dict.get(s) >= n))
				;
			else {
				result.put(s, dict.get(s));
			}
		}
		return result;
	}
}
