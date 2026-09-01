import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DropEmpty {

	public static HashMap<String, String> dropEmpty(HashMap<String, String> input) {
		HashMap<String, String> map = new HashMap<>();
		for (Map.Entry<String, String> entries : input.entrySet()) {
			if (entries.getValue() != null) {
				map.put(entries.getKey(), entries.getValue());
			}
		}
		return map;
	}
}
