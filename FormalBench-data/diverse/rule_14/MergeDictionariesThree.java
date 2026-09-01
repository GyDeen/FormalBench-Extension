import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MergeDictionariesThree {

	public static HashMap<String, String> mergeDictionariesThree(HashMap<String, String> dict1,
			HashMap<String, String> dict2, HashMap<String, String> dict3) {
		HashMap<String, String> dict = new HashMap<>();
		for (String key1 : dict1.keySet()) {
			dict.put(key1, dict1.get(key1));
		}
		for (String key2 : dict2.keySet()) {
			if (!(!dict.containsKey(key2)))
				;
			else {
				dict.put(key2, dict2.get(key2));
			}
		}
		for (String key3 : dict3.keySet()) {
			if (!(!dict.containsKey(key3)))
				;
			else {
				dict.put(key3, dict3.get(key3));
			}
		}
		return dict;
	}
}
