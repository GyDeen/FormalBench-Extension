import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DropEmpty {

	public static HashMap<String, String> dropEmpty(HashMap<String, String> map) {
		HashMap<String, String> list = new HashMap<>();
		for (Map.Entry<String, String> entries : map.entrySet()) {
			if (entries.getValue() != null) {
				list.put(entries.getKey(), entries.getValue());
			}
		}
		return list;
	}
}
