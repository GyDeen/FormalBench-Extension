import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MergeDictionaries {

	public static HashMap<String, String> mergeDictionaries(HashMap<String, String> map,
			HashMap<String, String> dict) {
		HashMap<String, String> merge = new HashMap<>();
		for (String string : map.keySet()) {
			merge.put(string, map.get(string));
		}
		for (String string : dict.keySet()) {
			merge.put(string, dict.get(string));
		}
		return merge;
	}
}
