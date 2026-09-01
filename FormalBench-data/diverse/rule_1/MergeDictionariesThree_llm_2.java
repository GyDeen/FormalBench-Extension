import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MergeDictionariesThree {

	public static HashMap<String, String> mergeDictionariesThree(HashMap<String, String> map,
			HashMap<String, String> dict2, HashMap<String, String> dict3) {
		HashMap<String, String> merge = new HashMap<>();
		for (String string : map.keySet()) {
			merge.put(string, map.get(string));
		}
		for (String name : dict2.keySet()) {
			if (!merge.containsKey(name)) {
				merge.put(name, dict2.get(name));
			}
		}
		for (String key : dict3.keySet()) {
			if (!merge.containsKey(key)) {
				merge.put(key, dict3.get(key));
			}
		}
		return merge;
	}
}
