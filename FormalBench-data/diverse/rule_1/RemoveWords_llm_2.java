
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveWords {

	public static String[] removeWords(String[] remove, String[] l) {
		Set<String> set = new HashSet<>(Arrays.asList(l));
		List<String> list = new ArrayList<>();

		for (String string : remove) {
			if (!set.contains(string)) {
				list.add(string);
			}
		}

		return list.toArray(new String[0]);
	}
}
