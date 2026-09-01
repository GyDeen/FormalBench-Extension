
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDuplicArray {

	public static String[] removeDuplicList(String[] array) {
		Set<String> sets = new HashSet<>();
		List<String> list = new ArrayList<>();
		for (String str : array) {
			if (!sets.contains(str)) {
				sets.add(str);
				list.add(str);
			}
		}
		return list.toArray(new String[0]);
	}
}
