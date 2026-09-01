import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddString {

	public static List<String> addString(List<Object> list, String string) {
		List<String> result = new ArrayList<>();
		for (int i = 0; list.size() > i; i++) {
			result.add(string.replace("{0}", String.valueOf(list.get(i))));
		}
		return result;
	}
}
