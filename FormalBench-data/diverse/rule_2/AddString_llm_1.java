import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddString {

	public static List<String> addString(List<Object> list, String string) {
		List<String> result = new ArrayList<>();
		int i = 0;
		while (i < list.size()) {
			result.add(string.replace("{0}", String.valueOf(list.get(i))));
			i++;
		}
		return result;
	}
}
