import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MergeDictionaries {

	public static HashMap<String, String> mergeDictionaries(HashMap<String, String> map,
			HashMap<String, String> dictionary) {
		HashMap<String, String> dict = new HashMap<>();
		for (String string : map.keySet()) {
			dict.put(string, map.get(string));
		}
		for (String string : dictionary.keySet()) {
			dict.put(string, dictionary.get(string));
		}
		return dict;
	}
}
