import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddString {

	public static List<String> addString(List<Object> list, String string) {
		List<String> result = new ArrayList<>();
		int id = 0;
		while (id < list.size()) {
			result.add(string.replace("{0}", String.valueOf(list.get(id))));
			id++;
		}
		return result;
	}
}
