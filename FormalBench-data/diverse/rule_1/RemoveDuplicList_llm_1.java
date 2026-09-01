
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDuplicArray {

	public static String[] removeDuplicList(String[] array) {
		Set<String> sets = new HashSet<>();
		List<String> remove = new ArrayList<>();
		for (String str : array) {
			if (!sets.contains(str)) {
				sets.add(str);
				remove.add(str);
			}
		}
		return remove.toArray(new String[0]);
	}
}
