
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveWords {

	public static String[] removeWords(String[] remove, String[] lists) {
		Set<String> set = new HashSet<>(Arrays.asList(lists));
		List<String> list = new ArrayList<>();

		for (String word : remove) {
			if (!set.contains(word)) {
				list.add(word);
			}
		}

		return list.toArray(new String[0]);
	}
}
