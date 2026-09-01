import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MergeDictionaries {

	public static HashMap<String, String> mergeDictionaries(HashMap<String, String> map,
			HashMap<String, String> dict) {
		HashMap<String, String> merge = new HashMap<>();
		for (String str : map.keySet()) {
			merge.put(str, map.get(str));
		}
		for (String str : dict.keySet()) {
			merge.put(str, dict.get(str));
		}
		return merge;
	}
}
